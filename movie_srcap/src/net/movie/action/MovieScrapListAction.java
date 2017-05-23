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
		String se_page = request.getParameter("se_page");
		
		
		MActionForward mforward = new MActionForward();
		
		MovieAPI mApi = new MovieAPI();
		mApi.setStx("");
		mApi.setSearch("title");
		mApi.setSe_page("");
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
		if(se_page == null){
			mApi.setSe_page("");
		} else if( se_page.equals("1")){
			mApi.setSe_page("1");
		} else if ( se_page.equals("2")){
			mApi.setSe_page("2");
		} else if ( se_page.equals("3")){
			mApi.setSe_page("3");
		} else if ( se_page.equals("4")){
			mApi.setSe_page("4");
		} else if ( se_page.equals("5")){
			mApi.setSe_page("5");
		} else if ( se_page.equals("6")){
			mApi.setSe_page("6");
		} else if ( se_page.equals("7")){
			mApi.setSe_page("7");
		} else if ( se_page.equals("8")){
			mApi.setSe_page("8");
		} else if ( se_page.equals("9")){
			mApi.setSe_page("9");
		} else if ( se_page.equals("10")){
			mApi.setSe_page("10");
		}
		
		MovieDAO mDao = MovieDAO.getInstance();
		ArrayList<Movie> movieList = mDao.getMovieList(mApi.getResult());
		
		int page = 1;
		int limit = 10;
		int listcount = movieList.size();
		int maxpage = (int)((double) listcount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1) endpage = startpage+10-1;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("movieList", movieList);
		
		mforward.setRedirect(false);
		mforward.setPath("movie/movie_list_api.jsp");
		
		return mforward;
	}

}
