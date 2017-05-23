package net.movie.action;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieDAO;

public class MovieScrapMyListAction implements MAction {

	@Override
	public MActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MActionForward forward = new MActionForward();
		
		HttpSession session = request.getSession();
		String mb_id = (String)session.getAttribute("mb_id");
		
		
		if(mb_id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		
		MovieDAO moviedao = MovieDAO.getInstance();
		List movielist = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = moviedao.getScrapCount(mb_id);
		movielist = moviedao.getScrapList(page, limit,mb_id);
		
		int maxpage = (int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("movielist", movielist);
		
		forward.setRedirect(false);
		forward.setPath("./movie/movie_my_list.jsp");
		return forward;
	}

}
