<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% session.getAttribute("mb_id"); %>
<c:import url="${URL }/layout/header_sub.jsp"></c:import>
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
<!-- 상단 시작 { -->
<div class="header_top_wrap">
	<div class="header_top clearfix">

		<!-- 글로벌메뉴 -->
		<div class="top_global_menu">
			<ul class="clear clearfix">
				<%if(session.getAttribute("mb_id") != null) { %>
				<li class="welcome_text"><%out.print(session.getAttribute("mb_id"));%> 님 환영합니다.</li>
				<li class="first"><a href="${PATH }/logout.me" id="logout">LOGOUT</a></li>
				<li><a href="${PATH }/memberCheckPass.me">MYPAGE</a></li>
				<li><a href="${PATH }/MovieMyList.mv">MY SCRAP</a></li>
				<%} else{%>
				<li class="first"><a href="${PATH }/member/loginForm.jsp">LOGIN</a></li>
				<li><a href="${PATH }/member/joinIntro.jsp">JOIN</a></li>
				<%} %>
				<li><a href="${PATH }/main.in">HOME</a></li>
			</ul>
		</div>
		<!-- //글로벌메뉴 -->
	</div>
</div>
<div class="header_wrap">
	<!-- 상단 -->
	<div class="header clearfix" id="header">
		<!-- logo -->
		<h1><a href="${PATH }/main.in" class="logo"><img src="${IMG_PATH }/common/logo.png?v=1" alt="무비스크랩" /></a></h1>
		<!-- //logo -->
		<!-- gnb_area -->
		<div class="gnb_area">
			<!-- main_menu -->
			<div class="main_menu">
				<h2 class="skip">메인메뉴</h2>
				<ul class="clear clearfix">
					<li><a href="${PATH }/MovieScrapList.mv">영화정보</a></li>
					<li><a href="${PATH }/BoardListAction.bo">토론방</a></li>
					<li><a href="${PATH }/SearchMap.so">영화관정보</a></li>
				</ul>
				<a href="javascript:;" class="btn_all_menu">전체메뉴보기</a>
				<a href="${PATH }/member/joinIntro.jsp" class="btn_join_go">회원가입</a>
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

