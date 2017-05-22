package net.member.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();

		boolean result = false;
		member.setMB_ID(request.getParameter("mb_id"));
		member.setMB_PW(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("auto_login"));

		Cookie[] cookies = request.getCookies();
		if (request.getParameter("auto_login") != null) {
			if (request.getParameter("auto_login").equals("on")) {
				if (cookies != null && cookies.length > 0) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("autologin")) {
							cookies[i].setMaxAge(60 * 60 * 24 * 30);
							response.addCookie(cookies[i]);
							System.out.println(cookies[i].getValue().toString());
						} else {
							Cookie cookie = new Cookie("autologin", member.getMB_ID());
							cookie.setMaxAge(60 * 60 * 24 * 30);
							response.addCookie(cookie);
							System.out.println(cookies[i].getValue().toString());
						}
					}
				} 
			}
		}
		else if(request.getParameter("auto_login")==null) {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("autologin")) {
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
						System.out.println(cookies[i].getValue().toString());
					}

				}
			}
		}
		System.out.println("로그인 시도");
		result = memberdao.LoginChk(member);

		String msg = "";

		if (result == true) {
			msg = member.getMB_NAME() + "님! 제가 얼마나 기다렸다구요! :)";
			System.out.println("로그인 성공");
			session.setAttribute("mb_id", member.getMB_ID());
		} else {
			msg = "이런..! 고객님이 입력하신 정보와 일치하는 계정을 찾을 수 없었어요. 혹시 아이디나 비밀번호가 기억나지 않는건 아닐까요? :(";
			System.out.println("로그인 실패");
		}
		request.setAttribute("msg", msg);
		request.setAttribute("result", result);
		return forward;
	}

}
