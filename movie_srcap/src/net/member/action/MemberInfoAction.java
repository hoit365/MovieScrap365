package net.member.action;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.member.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action{
	private MemberBean result;

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean rs = false;
		
		member.setMB_ID(request.getParameter("mb_id"));
		member.setMB_PW(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));
		result = memberdao.memberUpdate(member);
		
		if(result == null){
			System.out.println("회원 정보 조회 실패");
			String msg = "비밀번호가 잘못 입력 되었습니다. 다시 시도해주세요.";
			rs = false;
			request.setAttribute("result", rs);
			request.setAttribute("msg", msg);
			return forward;
		}
		rs = true;
		request.setAttribute("result", rs);
		System.out.println(result.toString());
		request.setAttribute("member", result);
		
		return forward;
	}
}
