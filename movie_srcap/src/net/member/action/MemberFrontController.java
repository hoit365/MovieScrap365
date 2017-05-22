package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		// 메인
		if (command.equals("/main.jsp")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./main/main.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}

		// 로그인 호출
		if (command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 회원 가입 전 약관 동의 페이지
		else if (command.equals("/MemberIntro.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/joinIntro.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 회원 정보 조회를 위한 비밀번호 확인 과정
		else if (command.equals("/memberCheckPass.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/memberCheckPass.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}

		// 회원 가입 창 호출
		else if (command.equals("/memberJoin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/memberForm.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 아이디 비밀번호 찾기 창 호출
		else if (command.equals("/findIdPw.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/findIdPw.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 회원 가입 종료
		else if (command.equals("/joinFin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/joinFin.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 아이디 찾기
		else if (command.equals("/findIdFin.me")) {
			action = new FindIdAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward.setRedirect(false);
			forward.setPath("./member/findIdFin.jsp");
		}
		// 패스워드 재설정
		else if (command.equals("/passwordReset.me")) {
			action = new PwResetAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
		}
		// 패스워드 찾기
		else if (command.equals("/findPwFin.me")) {
			action = new FindPwAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(request.getAttribute("result").toString());
			if ((boolean) request.getAttribute("result") == false) {
				forward.setRedirect(false);
				forward.setPath("./member/findIdPw.jsp");
			} else if ((boolean) request.getAttribute("result") == true) {
				forward.setRedirect(false);
				forward.setPath("./member/findPwFin.jsp");
			}
		}
		// 로그인 액션
		else if (command.equals("/login.me")) {
			action = new LoginAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ((boolean) request.getAttribute("result") == false) {
				forward.setRedirect(false);
				forward.setPath("./member/loginForm.jsp");
			} else {
				forward.setRedirect(false);
				forward.setPath("./main/main.jsp");
			}
		}
		// 패스워드 리셋
		else if (command.equals("/passwordReset.me")) {
			action = new LoginAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ((boolean) request.getAttribute("result") == false) {
				forward.setRedirect(false);
				forward.setPath("./member/errorSorry.jsp");
			} else {
				forward.setRedirect(false);
				forward.setPath("./main/main.jsp");
			}
		}
		// 회원 정보 조회
				else if (command.equals("/memberInfo.me")) {
					action = new MemberInfoAction();
					forward = new ActionForward();

					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if ((boolean) request.getAttribute("result") == false) {

						forward.setRedirect(false);
						forward.setPath("./member/memberCheckPass.jsp");
					} else if ((boolean) request.getAttribute("result") == true) {
						forward.setRedirect(false);
						forward.setPath("./member/memberInfo.jsp");
					}
				}
		// 회원 정보 수정
				else if (command.equals("/MemberInfoUpdate.me")) {
					action = new MemberInfoUpdateAction();
					forward = new ActionForward();

					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if ((boolean) request.getAttribute("result") == false) {

						forward.setRedirect(false);
						forward.setPath("./member/memberInfo.jsp");
					} else if ((boolean) request.getAttribute("result") == true) {
						forward.setRedirect(false);
						forward.setPath("./main/main.jsp");
					}
				}
		// 로그아웃
		if (command.equals("/logout.me")) {
			action = new LogoutAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward.setRedirect(false);
			forward.setPath("./main/main.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}
		// 회원 가입 액션
		else if (command.equals("/MemberJoinAction.me")) {
			action = new MemberJoinAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}