package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		AdminDAO adminDao = new AdminDAO();
		AdminBean adminBean = new AdminBean();

		boolean result = false;
		adminBean.setMB_ID(request.getParameter("mb_id"));
		adminBean.setMB_PW(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));

		
		
		System.out.println("ADMIN 로그인 시도");
		result = adminDao.LoginChk(adminBean);

		String msg = "";

		if (result == true) {
			msg = adminBean.getMB_NAME() + "님! 오늘 일이 많아요!! :)";
			System.out.println("로그인 성공");
			session.setAttribute("mb_id", adminBean.getMB_ID());
		} else {
			msg = "이런..! 당신 관리자가 아니군요? 당신은 대체....? :(";
			System.out.println("로그인 실패");
		}
		request.setAttribute("msg", msg);
		request.setAttribute("result", result);
		return forward;
	}

}
