package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class FindPwAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		member.setMB_ID(request.getParameter("mb_id"));
		member.setMB_PH(request.getParameter("mb_ph"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_ph"));
		System.out.println(member.toString());
		System.out.println("PASSWORD찾기 시도");
		result = memberdao.findMyPw(member);
		
		String[] msg;
		msg = new String[2];
		
		if(result == false){
			String msgPW = "이런..! 고객님의 비밀번호를 찾지 못했어요 아이디 또는 전화번호를 다시 입력해보세요 :(";

			request.setAttribute("msg", msgPW);
		}else if(result == true){
			msg[0] = "안심하세요 고객님 일치하는 계정을 찾았어요 :)";
			msg[1] = "고객님의 비밀번호를 재설정 해드릴께요";

			request.setAttribute("msgPw", msg);
		}
		System.out.println("msg::::::::"+msg[0]+msg[1]);
		request.setAttribute("msgPw", msg);
		request.setAttribute("member", member);
		request.setAttribute("result", result);
		
		return forward;
	}
}
