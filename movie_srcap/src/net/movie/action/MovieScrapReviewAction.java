package net.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieScrapReviewAction implements MAction{

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MActionForward forward = new MActionForward();
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("mb_id");
		
		boolean result = false;
		
		String seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		int rating = request.getParameter("ms_myRating") == null ? 0 : Integer.parseInt(request.getParameter("ms_myRating"));
		String review = request.getParameter("ms_review") == null ? "" : request.getParameter("ms_review");
		
		String title = request.getParameter("ms_title") == null? "" : request.getParameter("ms_title");
		
		System.out.println("MovieScrapReviewAction - ms_title : "+title);
		
		
		MovieDAO moviedao = MovieDAO.getInstance();
		MovieBean moviedata = new MovieBean();
		
		try {
			moviedata.setMb_id(mb_id);
			moviedata.setMs_title(title);
			moviedata.setMs_myRating(rating);
			moviedata.setMs_review(review);
			moviedata.setMs_seq(seq);
			moviedata.setMs_id(id);
			
			result = moviedao.MSReview(moviedata);
			if(result == false){
				System.out.println("리뷰 실패");
				return null;
			}
			System.out.println("리뷰 성공");
			
			forward.setRedirect(true);
			forward.setPath("./MovieScrapView.mv?id="+id+"&seq="+seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
		
	}

}
