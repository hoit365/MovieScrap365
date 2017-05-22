package net.member.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.member.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		member.setMB_ID(request.getParameter("mb_id"));
		member.setMB_PW(request.getParameter("mb_pw"));
		member.setMB_NAME(request.getParameter("mb_name"));
		member.setMB_BIRTH(request.getParameter("mb_birth"));
		member.setMB_GENDER(request.getParameter("mb_gender"));
		member.setMB_PH(request.getParameter("mb_ph"));
		member.setMB_EMAIL(request.getParameter("mb_email"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_name"));
		System.out.println(request.getParameter("mb_birth"));
		System.out.println(request.getParameter("mb_gender"));
		System.out.println(request.getParameter("mb_ph"));
		System.out.println(request.getParameter("mb_email"));
		System.out.println(member.toString());
		result = memberdao.joinMember(member);
		if(result == false){
			System.out.println("회원 가입 실패");
			return null;
		}
		
		//회원가입 성공
		forward.setRedirect(true);
		//forward.setPath("./MemberLogin.me");
		forward.setPath("./joinFin.me");
		
		System.out.println("회원 가입 완료");
		
		return forward;
	}
}
