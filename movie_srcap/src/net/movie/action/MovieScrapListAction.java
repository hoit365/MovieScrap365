package net.movie.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.movie.db.MovieAPI;
import net.movie.db.MovieDAO;


public class MovieScrapListAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("utf-8");
		String stx = request.getParameter("stx");
		MActionForward mforward = new MActionForward();
		
		MovieAPI mApi = new MovieAPI();
		mApi.setStx("");
		mApi.setSearch("title");
		if( stx != null ){
			mApi.setStx(stx);
		}
		
		MovieDAO mDao = MovieDAO.getInstance();
		ArrayList<Movie> movieList = mDao.getMovieList(mApi.getResult());
		
		request.setAttribute("movieList", movieList);
		
		mforward.setRedirect(false);
		mforward.setPath("movie/movie_list_api.jsp");
		
		return mforward;
	}

}
