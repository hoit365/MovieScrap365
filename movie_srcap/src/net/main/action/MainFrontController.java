package net.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.movie.action.MAction;
import net.movie.action.MActionForward;

public class MainFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		MainActionForward forward = null;
		MainAction action = null;
		
		System.out.println(command);
		
		if(command.equals("/main.in")){
			action = new MainIndexAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		doProcess(request,response);
	}
}
