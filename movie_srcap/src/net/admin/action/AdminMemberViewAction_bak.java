package net.admin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;
import net.admin.db.PageMaker;

public class AdminMemberViewAction_bak implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = new AdminDAO();
		List result = new ArrayList();
		AdminBean adminBean = new AdminBean();
		AdminBean pageing = new AdminBean();
		PageMaker pageMaker = new PageMaker();
		if(request.getParameter("search")==null){
			pageing = adminDAO.totalSearchList(adminBean);
			pageing = pageMaker.makeTotalPage(pageing);
			pageing = pageMaker.makeStoEPage(pageing);
			request.setAttribute("page", pageing.getPage());
			request.setAttribute("countList", pageing.getCountList());
			request.setAttribute("countPage", pageing.getCountPage());
			request.setAttribute("totalCount", pageing.getTotalCount());
			request.setAttribute("totalPage", pageing.getTotalPage());
			request.setAttribute("startPage", pageing.getStartPage());
			request.setAttribute("endPage", pageing.getEndPage());
			result = adminDAO.adminMemberView();
		
		}
		else if(request.getParameter("search")!=null){
			System.out.println(request.getParameter("search"));
			System.out.println(request.getParameter("searchKeyword"));
			if(request.getParameter("search")!=null){
				if(request.getParameter("search").equals("mb_id")){
					adminBean.setMB_ID(request.getParameter("searchKeyword"));
			}else if(request.getParameter("search").equals("mb_name")){
				adminBean.setMB_NAME(request.getParameter("searchKeyword"));
			}
			else if(request.getParameter("search").equals("mb_ph")){
				adminBean.setMB_PH(request.getParameter("searchKeyword"));
			}
			else if(request.getParameter("search").equals("mb_email")){
				adminBean.setMB_EMAIL(request.getParameter("searchKeyword"));
			}
			else if(request.getParameter("search").equals("mb_stat")){
				adminBean.setMB_STAT(request.getParameter("searchKeyword"));
			}
			}
			pageing = adminDAO.totalSearchList(adminBean);
			pageing = pageMaker.makeTotalPage(pageing);
			pageing = pageMaker.makeStoEPage(pageing);
			System.out.println(pageing.toString());
			request.setAttribute("page", pageing.getPage());
			request.setAttribute("countList", pageing.getCountList());
			request.setAttribute("countPage", pageing.getCountPage());
			request.setAttribute("totalCount", pageing.getTotalCount());
			request.setAttribute("totalPage", pageing.getTotalPage());
			request.setAttribute("startPage", pageing.getStartPage());
			request.setAttribute("endPage", pageing.getEndPage());
			
			result = adminDAO.adminMemberView(pageing);
		}
		
		request.setAttribute("memberList", result);
		
		System.out.println("회원 정보 조회 완료");
		return forward;
	}
}
