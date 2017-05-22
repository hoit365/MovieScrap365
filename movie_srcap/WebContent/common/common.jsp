<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//경로설정
	String path = request.getContextPath();
	application.setAttribute("PATH", path );
	application.setAttribute("JS_PATH", path +"/public/js");
	application.setAttribute("CSS_PATH", path +"/public/css");
	application.setAttribute("IMG_PATH", path +"/public/images");
	
	String requestURL = request.getRequestURL().toString();
	String contextPath = request.getContextPath();
	String URL = requestURL.substring(0,requestURL.indexOf(contextPath)+contextPath.length());
	application.setAttribute("URL", URL);
%>

