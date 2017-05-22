package net.movie.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.ActionForward;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieReviewExistence implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MActionForward forward = new MActionForward();
		
		request.setCharacterEncoding("utf-8");
		boolean result = false;
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("mb_id");
		
		String seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		boolean click = request.getParameter("click") == null ? false : Boolean.parseBoolean(request.getParameter("click"));
		
		if(mb_id == null) {
			System.out.println("MovieReviewEx session null!!!!!");
			String msg = "로그인이 필요합니다";
			request.setAttribute("msg", msg);
			forward.setPath("/MemberLogin.me?&ms_seq="+seq+"&ms_id="+id);
			
			return forward;
		}
		
		System.out.println("mbid : "+mb_id);
		System.out.println("seq : "+seq);
		System.out.println("id : "+id);
		
		MovieDAO mDAO = MovieDAO.getInstance();
		MovieBean moviedata = new MovieBean();
		
		boolean isWriter = mDAO.isReviewWriter(mb_id, seq, id);
		
		System.out.println("moviereviewex mbid : "+mb_id);
		System.out.println("isWriter : "+isWriter);
		
		forward.setRedirect(false);
		forward.setPath("./MovieScrapView.mv?id="+id+"&seq="+seq+"&isWriter="+isWriter+"&click"+click);
		
		return forward;
	}

}
