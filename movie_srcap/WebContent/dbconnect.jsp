<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Oracle Connection Test</title>
</head>
<body>
<%@ page import="java.sql.*" %>
<h2>데이터베이스 드라이버와 DB Oracle 연결 점검 프로그램</h2>
<%
	try{
		String driverName="oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@172.16.1.50:1521:xe";
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(dbURL,"soldesk","2017");
		out.println("Oracle Database 접속.");
		conn.close();
	}catch(Exception e){
		out.println("Oracle Database 접속 문제 발생.");
		out.println(e.getMessage());
		e.printStackTrace();
	}
%>
<hr size="5" color="red"/>
</body>
</html>