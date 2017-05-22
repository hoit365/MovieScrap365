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
		String mb_id = (String) session.getAttribute("mb_id");
		
		boolean result = false;
		boolean isScrapWriter = false;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			//seq - movieSeq / id - movieid 값 받음
			String ms_seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
			String ms_id = request.getParameter("id") == null ? "" : request.getParameter("id");
			
			
			moviedata.setMb_id(mb_id);
			moviedata.setMs_seq(ms_seq);
			moviedata.setMs_id(ms_id);
			
			if(moviedao.MSScrapDeleteCheck(mb_id, ms_seq, ms_id)){
				System.out.println("스크랩안됨!!!");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아직 스크랩 안한 영화입니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
				
			} else {
			
				result = moviedao.MSScrapDelete(moviedata);
				
				
				if(result == false){
					System.out.println("스크랩 삭제 실패 !!");
					return null;
				} else {
					System.out.println("스크랩 삭제 성공!!!");
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('스크랩삭제성공');");
					out.println("history.go(-1);");
					out.println("</script>");
				}
				
				return null;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
