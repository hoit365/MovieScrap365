<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<% session.getAttribute("mb_id"); %>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<div class="login_area">
				<div class="login_inner">
				<h2 class="login_logo">MYPAGE 비밀번호 확인</h2>
				<form action="${PATH }/memberInfo.me" method="post">
					<input type="hidden" name="mb_id" value="${mb_id }" />
					<fieldset>
						<h3>MYPAGE</h3>
						<div class="login_input clearfix">
							<ul class="clear">
								<li class="id">ID : ${mb_id }</li>
								<li class="pw">PW : 
									<label for="login_pw" class="skip">비밀번호<strong class="sound_only"> 필수</strong></label>
									<input type="password" name="mb_pw" id="login_pw" required="" class="input-text" size="20" maxlength="20" placeholder="비밀번호">
								</li>
							</ul>
							
							<input type="submit" value="LOGIN" class="btn_login">
						</div>
					</fieldset>
				</form>
				<p class="hint">${message }</p>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="../layout/footer.jsp"></c:import>