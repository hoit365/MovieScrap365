package net.map.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.map.action.ActionFoward;
import net.map.action.Action;

public class MapFrontController extends HttpServlet implements javax.servlet.Servlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = requestURI.substring(contextPath.length());
    	
    	ActionFoward forward = null;
    	Action action = null;
    	
    	System.out.println("MapFrontController.doProcess() : requestURI|" + requestURI +"|");
    	System.out.println("MapFrontController.doProcess() : contextPath|" + contextPath +"|");
    	System.out.println("MapFrontController.doProcess() : command|" + command +"|");
    	   	
    	if(command.equals("/SearchMap.so")) {
    		forward = new ActionFoward();
    		forward.setRedirect(false);
    		forward.setPath("./theaterMap/searchMap.jsp");
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			//단순 페이지 Redirection
    			response.sendRedirect(forward.getPath());
    		} else{
    			// Action이 request에 Attribute로 저장한 데이터를 활용하여 View 페이지를 작성한다.
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
