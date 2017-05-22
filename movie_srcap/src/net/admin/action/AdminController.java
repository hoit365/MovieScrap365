package net.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class AdminController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		// 메인
		if (command.equals("/admin.ad")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./admin/page/adminLogin.jsp");
			System.out.println("front open");
			System.out.println(action);
			System.out.println(forward);

		}

		
		// 로그인 액션계열
		else if (command.equals("/member_list.ad")) {
			action = new AdminLoginAction();
			forward = new ActionForward();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ((boolean) request.getAttribute("result") == false) {
				forward.setRedirect(false);
				forward.setPath("./admin/page/adminLogin.jsp");
			} else {
				forward.setRedirect(false);
				forward.setPath("./admin/page/member_list.jsp");
			}
		}
		
		// 로그아웃
		if (command.equals("/logout.me")) {
			action = new AdminLogoutAction();
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