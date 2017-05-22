<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<script type="text/javascript">
<% 
String[] msgId ;
msgId = new String[2];
msgId = (String[])request.getAttribute("msgId");
System.out.println(msgId[0].toString());
%>
</script>

<!-- content -->
<section class="content">
	<div class="content_area joinFin_area clearfix">
		<div class="section">
			<h2 class="title01"> ${msgId[0] } </h2>
			<center><p>${msgId[1] }</p></center>
			<div class="join_img"><img src="${IMG_PATH }/common/logo.png" alt="" /></div>
			<div class="btn_area_center">
				<a href="${PATH }/member/loginForm.jsp" class="btn02">로그인</a>
				<a href="${PATH }/main/main.jsp" class="btn02">메인으로</a>
			</div>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>