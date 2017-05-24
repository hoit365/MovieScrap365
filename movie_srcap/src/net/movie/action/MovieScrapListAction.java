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
		String sort= request.getParameter("sort");
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		
		MActionForward mforward = new MActionForward();
		
		MovieAPI mApi = new MovieAPI();
		mApi.setSearch("title");
		mApi.setPage(page);
		
		if( stx != null ){
			mApi.setStx(stx);
		}
		
		if(sort == null){
			mApi.setSort("");
		} else if( sort.equals("prodYear")){
			mApi.setSort("prodYear");
		} else if ( sort.equals("title")){
			mApi.setSort("title");
		}

		MovieDAO mDao = MovieDAO.getInstance();
		ArrayList<Movie> movieList = mDao.getMovieList(mApi.getResult());

		int limit = 10;
		int listcount = movieList.size();
		int maxpage = (int)((double) listcount/limit+0.95);;
		int endpage = maxpage;
		int startpage = (((int)((double)1/10+0.9))-1)*10+1;
		
		if(endpage>startpage+10-1) endpage=startpage+10-1;

		
		request.setAttribute("page", page);
		request.setAttribute("endpage", endpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("movieList", movieList);
		System.out.println(endpage+"/"+startpage+"/"+movieList.size());
		
		mforward.setRedirect(false);
		mforward.setPath("movie/movie_list_api.jsp");
		
		return mforward;
	}

}
