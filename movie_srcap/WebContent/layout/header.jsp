<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<c:import url="${URL }/layout/header_sub.jsp"></c:import>
<!-- 상단 시작 { -->
<div class="header_top_wrap">
	<div class="header_top clearfix">
		<!-- 공지사항 -->
		<div class="top_notice">
			<h2>NOTICE</h2>
			<div class="top_notice_viewer">
				<div class="list_view">
					<ul class="clear">
						<li>공지사항입니다.1</li>
						<li>공지사항입니다.2</li>
						<li>공지사항입니다.3</li>
						<li>공지사항입니다.4</li>
						<li>공지사항입니다.5</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- //공지사항 -->
		<!-- 글로벌메뉴 -->
		<div class="top_global_menu">
			<ul class="clear clearfix">
				<%if(session.getAttribute("mb_id") != null) { %>
				<li class="welcome_text"><%out.print(session.getAttribute("mb_id"));%> 님 환영합니다.</li>
				<li class="first"><a href="${PATH }/logout.me" id="logout">LOGOUT</a></li>
				<li><a href="${PATH }/memberCheckPass.me">MYPAGE</a></li>		
				<%} else{%>
				<li class="first"><a href="${PATH }/member/loginForm.jsp">LOGIN</a></li>
				<li><a href="${PATH }/member/joinIntro.jsp">JOIN</a></li>
				<%} %>
				<li><a href="${PATH }/index.jsp">HOME</a></li>	
				
			</ul>
		</div>
		<!-- //글로벌메뉴 -->
	</div>
</div>
<div class="header_wrap">
	<!-- 상단 -->
	<div class="header clearfix" id="header">
		<!-- logo -->
		<h1><a href="${PATH }/index.jsp" class="logo"><img src="${IMG_PATH }/common/logo.png?v=1" alt="무비스크랩" /></a></h1>
		<!-- //logo -->
		<!-- gnb_area -->
		<div class="gnb_area">
			<!-- main_menu -->
			<div class="main_menu">
				<h2 class="skip">메인메뉴</h2>
				<ul class="clear clearfix">
					<li><a href="${PATH }/MovieScrapList.mv">영화정보</a></li>
					<li><a href="#">매거진</a></li>
					<li><a href="${PATH }/BoardListAction.bo">토론방</a></li>
					<li><a href="${PATH }/SearchMap.so">영화관정보</a></li>
				</ul>
				<a href="javascript:;" class="btn_all_menu">전체메뉴보기</a>
				<a href="${PATH }/member/join.do" class="btn_join_go">회원가입</a>
			</div>
			<div class="main_bottom_menu">
				<ul class="clear clearfix">
					<li><a href="">2016 - Concept1</a></li>
					<li><a href="">2015 - Concpet2</a></li>
					<li><a href="">2014 - Concpet3 </a></li>
					<li><a href="">2013 - collection</a></li>
				</ul>
			</div>
			<!-- //main_menu -->
		</div>
		<!-- //gnb_area -->
	</div>
	<!-- //상단 -->
</div>
<!-- } 상단 끝 -->
<hr />
<!-- 콘텐츠 시작 { -->
<div class="container_wrap">
	<div class="container" id="container">

