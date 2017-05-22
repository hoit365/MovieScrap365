package net.movie.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieAPI;
import net.movie.db.MovieDAO;

public class MovieScrapViewAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MActionForward mforward = new MActionForward();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("mb_id");
		
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
		boolean isWriter = request.getParameter("isWriter") == null ? true : Boolean.parseBoolean(request.getParameter("isWriter"));
		boolean click = request.getParameter("click") == null ? false : Boolean.parseBoolean(request.getParameter("click"));
		
		System.out.println("moviescrapview isWriter : "+isWriter);
		
		List reviewlist = new ArrayList();
		
		int spage = 1;
		int limit = 5;
		
		if(request.getParameter("page") != null) {
			spage = Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("MovieScriapviewaction의 spage : "+spage);
		
		int listcount = mDao.getListcount(seq, id);
		System.out.println("moviescrapviewaction의 listcount : "+listcount);
		reviewlist = mDao.getReviewList(spage, limit);
		
		int maxPage = ((listcount-1)/limit) + 1;		
		
		int startPage = spage <= 2 ? 1 : (spage > maxPage-2? maxPage-4 : spage-2);
		int endPage = spage > maxPage-2? maxPage: startPage+4;
		
		if(endPage > maxPage){
			endPage = maxPage;
		}
		
		request.setAttribute("session_mb_id", mb_id);
		request.setAttribute("click", click);
		request.setAttribute("isWriter", isWriter);
		request.setAttribute("spage", spage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage );
		request.setAttribute("listcount", listcount);
		request.setAttribute("reviewlist", reviewlist);
		System.out.println(mApi.getResult());
		//view 설정
		mforward.setRedirect(false);
		mforward.setPath("movie/movie_view.jsp");
		
		return mforward;
		
	}

}
