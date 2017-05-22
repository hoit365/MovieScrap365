<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="apple-mobile-web-app-title" content="">
	<meta name="format-detection" content="telephone=no" />
	<meta name="HandheldFriendly" content="true" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10,user-scalable=yes" />	
	<link href="" rel="shortcut icon">
	<!--[if IE 7]><html lang="ko" class="ie7"><![endif]-->
	<!--[if IE 8]><html lang="ko" class="ie8"><![endif]-->
	<title>무비스크랩 대시보드</title>
	<link rel="stylesheet" type="text/css" href="${CSS_PATH }/admin.css?v=<%=System.currentTimeMillis() %>" />
	<script type="text/javascript" src="${JS_PATH }/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${JS_PATH }/jquery-ui.js"></script>
	<!--[if lte IE 8]>
	<script type="text/javascript" src="${JS_PATH }/respond.js"></script>
	<script type="text/javascript" src="${JS_PATH }/html5.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${JS_PATH }/ux.js?v=<%=System.currentTimeMillis() %>"></script>
	<% session.getAttribute("mb_id"); %>
<script type="text/javascript">
<%
if(request.getAttribute("msg")!=null){
	String msg = "";
	msg = (String)request.getAttribute("msg");
	System.out.println(msg);
%>
	alert("<%=msg%>");
<%
	request.setAttribute("msg", null);
	System.out.println((String)request.getAttribute("msg"));
}
%>
</script>
</head>
<body>
<div class="wrap">
	<!-- header -->
	<div class="header_area">
		<header id="header" class="header">
			<h1 class="logo">Dashboard</h1>
			<div class="gnb_area">
				<ul class="clear clearfix">
					<li><a href="">회원관리</a></li>
					<li><a href="">게시물관리</a></li>
					<li><a href="">메인관리</a></li>
					<li><a href="">스크랩관리</a></li>
				</ul>			
			</div>
		</header>
	</div>
	<!-- //header -->
	<!-- container -->
	<div class="container" id="container">
		<div class="content_area">
