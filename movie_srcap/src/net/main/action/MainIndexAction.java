package net.main.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
		forward.setRedirect(false);
		forward.setPath("./main/main.jsp");
		
		return forward;
	}

}
