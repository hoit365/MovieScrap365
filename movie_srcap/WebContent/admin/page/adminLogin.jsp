<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<form action="${PATH }/member_list.ad" method="post">
<div class="login_area">
	<h2 class="login_title"><span class="icon_login"></span>Dashboard Login</h2>
	<div class="login_list">
		<ul class="clear">
			<li class="icon_id"><span>ID</span><input type="text" placeholder="id" name="mb_id"></li>
			<li class="icon_pw"><span>PW</span><input type="password" placeholder="password" name="mb_pw"></li>
		</ul>
		<div class="login_btn">
			<input type="submit" class="btn_login" value="LOGIN">
		</div>
		<div class="login_bottom">
			<a href="${PATH}/admin/admin.ad">메인으로</a>
		</div>
	</div>
</div>
</form>
<c:import url="../layout/footer.jsp"></c:import>