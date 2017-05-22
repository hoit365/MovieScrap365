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

public class MovieScrapAddAction implements MAction{
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MovieDAO moviedao =  MovieDAO.getInstance();
		MovieBean moviedata = new MovieBean();
		MActionForward forward = new MActionForward();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("mb_id");
		
		boolean result = false;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			//seq - movieSeq / id - movieid 값 받음
			String seq = request.getParameter("seq") == null ? "" : request.getParameter("seq");
			String id = request.getParameter("id") == null ? "" : request.getParameter("id");
			
			//API 검색 설정
			MovieAPI mApi = new MovieAPI();
			mApi.setSearch("movieSeq");
			mApi.setStx(seq);
			mApi.setSearch2("movieId");
			mApi.setStx2(id);
			
			//검색한 영화 데이터 가져오기.
			MovieDAO mDao = MovieDAO.getInstance();
			ArrayList<Movie> movieList = mDao.getMovieList(mApi.getResult());
			Movie movie = movieList.get(0);
			request.setAttribute("movie", movie);
			
			moviedata.setMb_id(mb_id);
			String poster = "";
			
			if(movie.getPoster().equals("")){
				poster = "";
			} else if(movie.getPoster() != null) {
				StringTokenizer st = new StringTokenizer(movie.getPoster(),"%|");
				poster = st.nextToken();
			} else {
				poster = "";
			}
			
			
			
			moviedata.setMs_title(movie.getTitle());
			moviedata.setMs_subtitle(movie.getTitleOrg());
			moviedata.setMs_director(movie.getDirector());
			moviedata.setMs_poster(poster);
			moviedata.setMs_rating(movie.getRating());
			moviedata.setMs_seq(seq);
			moviedata.setMs_id(id);
			
			if(moviedao.MSScrapCheck(mb_id, seq, id) == false){
				System.out.println("중복이양!!!");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이미 스크랩하신 영화입니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			} else {
				result = moviedao.MSScrap(moviedata);
				
				if(result == false){
					System.out.println("스크랩 실패 !!");
					return null;
				} else {
					System.out.println("스크랩 성공!!!");
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('스크랩성공');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.close();
					
					if(moviedao.MovieRankCheck(seq, id)){
						result = moviedao.MovieRankAdd(moviedata);
						if(result == false){
							System.out.println("랭킹 추가 실패");
							return null;
						} else {
							System.out.println("랭킹 추가 성공");
						}
						
					} else {
						moviedao.setMovieRankCntUp(seq, id);
					}
					
				}
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
