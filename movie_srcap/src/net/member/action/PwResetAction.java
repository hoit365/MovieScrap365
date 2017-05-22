package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class PwResetAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		member.setMB_ID(request.getParameter("mb_id"));
		member.setMB_PW(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));
		System.out.println(member.toString());
		System.out.println("PASSWORD 재설정 시도");
		result = memberdao.MemberPwUpdate(member);
		
		String msg = "";
		
		if(result == false){
			msg = "자그마한 오류로 고객님의 패스워드가 변경되지 않았어요..";
		}else if(result == true){
			msg = "고객님의 패스워드가 안전하게 변경되었어요.";
		}
		System.out.println("msg::::::::"+msg);
		request.setAttribute("msg", msg);
		
		return forward;
	}
}
