package net.admin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;
import net.admin.db.AdminDAO2;
import net.admin.db.PageMaker;

public class AdminMemberViewAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		
		String search = request.getParameter("search") == null ? null : request.getParameter("search");
		String searchKeyword = request.getParameter("searchKeyword") == null ? null : request.getParameter("searchKeyword");
		String get_id = request.getParameter("mb_id") == null ? null : request.getParameter("mb_id");
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		int firstRow = 0; //첫번째 행
		int endRow = 0; //마지막행
		
		ActionForward forward = new ActionForward();
		AdminDAO2 adminDAO = AdminDAO2.getInstance();
		
		int totalCount = adminDAO.getBoardTotalCount(search, searchKeyword);
		if( totalCount > 0){
			firstRow = (page - 1) * 10+1;
			endRow = firstRow + 10;
		}
		
		int maxpage = (int)((double) totalCount/10+0.95);
		int endpage = maxpage;
		int startpage = (((int)((double)1/10+0.9))-1)*10+1;
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		request.setAttribute("page", page);
		request.setAttribute("endpage", endpage);
		request.setAttribute("startpage", startpage);
		
		//멤버리스트
		ArrayList<AdminBean> resultList = adminDAO.getAdminList(search, searchKeyword, firstRow, endRow);
		request.setAttribute("resultList", resultList);
		request.setAttribute("listcount", resultList.size());
		
		//상세보기
		AdminBean viewBean = null;
		if( get_id != null ){
			viewBean = adminDAO.adminMemberView(get_id);
			request.setAttribute("viewBean", viewBean);
		}
		return forward;
	}
}
