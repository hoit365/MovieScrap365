package net.movie.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieAPI;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieScrapDeleteAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MovieDAO moviedao =  MovieDAO.getInstance();
		MovieBean moviedata = new MovieBean();
		MActionForward forward = new MActionForward();
		
		HttpSession session = request.getSession();
		String mb_id = session.getAttribute("mb_id") == null ? null : (String) session.getAttribute("mb_id");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		boolean result = false;
		boolean isScrapWriter = false;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			//seq - movieSeq / id - movieid 값 받음
			String ms_seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
			String ms_id = request.getParameter("id") == null ? "" : request.getParameter("id");
			//String p = request.getParameter("p") == null ? "list" : request.getParameter("p");
			
			moviedata.setMb_id(mb_id);
			moviedata.setMs_seq(ms_seq);
			moviedata.setMs_id(ms_id);
			

			
			if(moviedao.MSScrapDeleteCheck(mb_id, ms_seq, ms_id)){
				out.print("스크랩 하지 않은 영화입니다.");
			} else {
				result = moviedao.MSScrapDelete(moviedata);
				if(result == false){
					out.print("스크랩 삭제 실패.");
				} else {
					out.print("스크랩 삭제 성공.");
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
