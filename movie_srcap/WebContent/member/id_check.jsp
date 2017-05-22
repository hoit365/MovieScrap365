<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String mb_id = request.getParameter("mb_id");
%>
<jsp:useBean id="dao" class="net.member.db.MemberDAO"></jsp:useBean>
<% boolean is_id = dao.dupChk(mb_id); %>
<%=is_id %>
