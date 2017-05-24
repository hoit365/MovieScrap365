package net.main.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.action.Movie;
import net.movie.db.MovieAPI;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MainIndexAction implements MainAction {

	@Override
	public MainActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MainActionForward forward = new MainActionForward();
		
		MovieDAO moviedao = MovieDAO.getInstance();
		ArrayList<MovieBean> movieLankList = moviedao.getRankList();
		request.setAttribute("movieLankList", movieLankList);

		MovieAPI mApi = new MovieAPI();
		mApi.setSort("prodYear");
		mApi.setPage(1);
		mApi.setListCount(100);
		
		ArrayList<Movie> movieLastestList = moviedao.getMovieList(mApi.getResult());
		request.setAttribute("movieLastestList", movieLastestList);
		
		
		//최신작1
		MovieAPI mApi1 = new MovieAPI();
		mApi1.setSearch("movieSeq");
		mApi1.setStx("39074");
		mApi1.setSearch2("movieId");
		mApi1.setStx2("F");
		ArrayList<Movie> mv1_list = moviedao.getMovieList(mApi1.getResult());
		Movie mv1 = mv1_list.get(0);
		request.setAttribute("mv1", mv1);
		
		//최신작2 seq=36279&id=F
		MovieAPI mApi2 = new MovieAPI();
		mApi2.setSearch("movieSeq");
		mApi2.setStx("36279");
		mApi2.setSearch2("movieId");
		mApi2.setStx2("F");
		ArrayList<Movie> mv2_list = moviedao.getMovieList(mApi2.getResult());
		Movie mv2 = mv2_list.get(0);
		request.setAttribute("mv2", mv2);
		
		
		forward.setRedirect(false);
		forward.setPath("./main/main.jsp");
		
		return forward;
	}

}
