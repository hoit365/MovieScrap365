package net.member.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import net.member.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoUpdateAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		HttpSession session = request.getSession();
		
		boolean result = false;
		member.setMB_ID((String) session.getAttribute("mb_id"));
		member.setMB_PW(request.getParameter("mb_pw"));
		member.setMB_PH(request.getParameter("mb_ph"));
		member.setMB_EMAIL(request.getParameter("mb_email"));
		System.out.println(request.getParameter(member.toString()));
		result = memberdao.MemberInfoUpdate(member);
		if(result == false){
			request.setAttribute("result", result);
			System.out.println("회원 정보 수정 실패");
			return null;
		}
		request.setAttribute("result", result);
		//회원가입 성공
		forward.setRedirect(true);
		//forward.setPath("./MemberLogin.me");
		forward.setPath("./joinFin.me");
		
		System.out.println("회원 가입 완료");
		
		return forward;
	}
}
