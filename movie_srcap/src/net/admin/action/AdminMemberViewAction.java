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

		//멤버리스트
		ArrayList<AdminBean> resultList = adminDAO.getAdminList(search, searchKeyword, firstRow, endRow);
		request.setAttribute("resultList", resultList);
		
		System.out.println("회원 정보 조회 완료");
		return forward;
	}
}
