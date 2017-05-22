package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class FindIdAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Action 진입");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String result = "";
		member.setMB_NAME(request.getParameter("mb_name"));
		member.setMB_PH(request.getParameter("mb_ph"));
		System.out.println(request.getParameter("mb_name"));
		System.out.println(request.getParameter("mb_ph"));
		System.out.println("ID찾기 시도");
		result = memberdao.findMyId(member);
		
		String[] msg;
		msg = new String[2];
		
		if(result.equals("")){
			msg[0] = "이런..! 고객님의 아이디를 찾지 못했어요 :(";
			msg[1] = "이름 또는 전화번호를 다시 입력해보세요";
		}else{
			msg[0] = "안심하세요 고객님의 아이디를 찾았습니다.";
			msg[1] = "회원님의 아이디는 "+result+" 입니다.";
		}
		System.out.println("msg::::::::"+msg[0]+msg[1]);
		request.setAttribute("msgId", msg);
		
		return forward;
	}
}
