package net.admin.action;

import java.util.ArrayList;
import java.util.List;

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
		List memberlist = new ArrayList();
		int result = 4;
		adminBean.setMB_ID(request.getParameter("mb_id"));
		adminBean.setMB_PW(request.getParameter("mb_pw"));
		System.out.println(request.getParameter("mb_id"));
		System.out.println(request.getParameter("mb_pw"));

		
		
		System.out.println("ADMIN 로그인 시도");
		result = adminDao.LoginChk(adminBean);
		

		String msg = "";

		if (result == 0) {
			msg = adminBean.getMB_NAME() + "님! 오늘 일이 많아요!! :)";
			System.out.println("로그인 성공");
			session.setAttribute("mb_id", adminBean.getMB_ID());
			memberlist = adminDao.adminMemberAllView();
		} else if (result == 1){
			msg ="비밀번호가 틀렸어요 :(";
		}
		else if (result == 2){
			msg ="회원님 관리자만 출입할 수 있어요!";
		}
		else if (result == 3){
			msg ="비활성화된 관리자 계정입니다, 다른 계정을 사용하거나 전산팀으로 문의 주세요.(TEL : 010-3306-3382)";
		}
		else if (result == 4){
			msg ="알 수 없는 오류 입니다, 전산팀으로 문의 주세요.(TEL : 010-3306-3382)";
		}else{
			msg ="알 수 없는 오류 입니다, 전산팀으로 문의 주세요.(TEL : 010-3306-3382)";
		}

		
		request.setAttribute("result", result);
		request.setAttribute("memberList", memberlist);
		request.setAttribute("msg", msg);
		request.setAttribute("result", result);
		return forward;
	}

}
