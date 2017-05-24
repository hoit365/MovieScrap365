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
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = new AdminDAO();
		boolean updater = false;
		List result = new ArrayList();
		AdminBean adminBean = new AdminBean();
		AdminBean pageing = new AdminBean();
		PageMaker pageMaker = new PageMaker();
		String msg = "";
		
		adminBean.setMB_ID(request.getParameter("mb_id"));
		adminBean.setMB_NAME(request.getParameter("mb_name"));
		adminBean.setMB_BIRTH(request.getParameter("mb_birth"));
		adminBean.setMB_GENDER(request.getParameter("mb_gender"));
		adminBean.setMB_PH(request.getParameter("mb_ph"));
		adminBean.setMB_EMAIL(request.getParameter("mb_email"));
		adminBean.setMB_STAT(request.getParameter("mb_stat"));
		
		updater = adminDAO.AdminMemberInfoUpdate(adminBean);
		
		if(updater == false){
			System.out.println("정보수정 실패");
		msg ="알 수 없는 오류 입니다, 전산팀으로 문의 주세요.(TEL : 010-3306-3382)";
		}
		else if(updater == true){
			System.out.println("정보수정 성공");
			msg ="회원 정보가 수정 되었습니다.";
		}
		
		pageing = adminDAO.totalSearchList(pageing);
		pageing = pageMaker.makeTotalPage(pageing);
		pageing = pageMaker.makeStoEPage(pageing);
		System.out.println(pageing.toString());
		result = adminDAO.adminMemberView();
		
		request.setAttribute("page", pageing.getPage());
		request.setAttribute("countList", pageing.getCountList());
		request.setAttribute("countPage", pageing.getCountPage());
		request.setAttribute("totalCount", pageing.getTotalCount());
		request.setAttribute("totalPage", pageing.getTotalPage());
		request.setAttribute("startPage", pageing.getStartPage());
		request.setAttribute("endPage", pageing.getEndPage());
		request.setAttribute("memberList", result);
		request.setAttribute("msg", msg);
		return forward;
	}

}
