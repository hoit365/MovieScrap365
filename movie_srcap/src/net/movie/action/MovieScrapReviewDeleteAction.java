package net.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieScrapReviewDeleteAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MActionForward forward = new MActionForward();
		request.setCharacterEncoding("utf-8");
		boolean result = false;
		
		
		String seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		String mb_id = request.getParameter("mb_id") == null ? "" : request.getParameter("mb_id");
	
		MovieDAO mDAO = MovieDAO.getInstance();
		MovieBean moviedata = new MovieBean();
		
		try {
			moviedata.setMb_id(mb_id);
			moviedata.setMs_seq(seq);
			moviedata.setMs_id(id);
			
			result = mDAO.MSReviewdelete(moviedata);
			if(result == false){
				System.out.println("리뷰 삭제 실패");
				return null;
			}
			System.out.println("리뷰 삭제 성공");
			
			forward.setRedirect(true);
			forward.setPath("./MovieScrapView.mv?id="+id+"&seq="+seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
