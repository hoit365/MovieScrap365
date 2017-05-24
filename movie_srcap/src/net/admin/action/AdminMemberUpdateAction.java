package net.admin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;
import net.admin.db.PageMaker;

public class AdminMemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = new AdminDAO();
		boolean result = false;
		request.setCharacterEncoding("utf-8");

	
		String search = request.getParameter("search") == null ? null : request.getParameter("search");
		String searchKeyword = request.getParameter("searchKeyword") == null ? null : request.getParameter("searchKeyword");
		String get_id = request.getParameter("mb_id") == null ? null : request.getParameter("mb_id");
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
	
		String msg = "";
		
		AdminBean adminBean = new AdminBean();
		adminBean.setMB_ID(request.getParameter("mb_id"));
		adminBean.setMB_NAME(request.getParameter("mb_name"));
		adminBean.setMB_BIRTH(request.getParameter("mb_birth"));
		adminBean.setMB_GENDER(request.getParameter("mb_gender"));
		adminBean.setMB_PH(request.getParameter("mb_ph"));
		adminBean.setMB_EMAIL(request.getParameter("mb_email"));
		adminBean.setMB_STAT(request.getParameter("mb_stat"));

		result = adminDAO.AdminMemberInfoUpdate(adminBean);
		
		if( result ){
			System.out.println("성공");
		}else{
			System.out.println("실패");
		}
		forward.setRedirect(true);
		forward.setPath("./member_list.ad");
		
		//request.setAttribute("msg", msg);
		return forward;
	}

}
