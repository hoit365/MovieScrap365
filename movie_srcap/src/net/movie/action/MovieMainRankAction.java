package net.movie.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieMainRankAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MActionForward forward = new MActionForward();
		
		MovieDAO moviedao = MovieDAO.getInstance();
		List movierankList = new ArrayList();
		

		
		
		request.setAttribute("movierankList", movierankList);
		
		forward.setRedirect(false);
		forward.setPath("./movie/movie_rank.jsp");
		
		return forward;
	}

}
