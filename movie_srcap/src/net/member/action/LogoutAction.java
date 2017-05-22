package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String msg;
		
		if(session.getAttribute("mb_id")!=null){
			
			session.invalidate();
			msg = "안전하게 로그아웃 되었어요 다음에 또봐요 :)";
			request.setAttribute("session", session);
			
		}
		else{
			return null;
		}
		request.setAttribute("msg", msg);
		return forward;
}
}