package net.admin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminDAO;

public class AdminMemberViewAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = new AdminDAO();


		List result = new ArrayList();
		
		result = adminDAO.adminMemberAllView();
		
		request.setAttribute("result", result);
		
		System.out.println("회원 정보 조회 완료");
		
		return forward;
	}
}
