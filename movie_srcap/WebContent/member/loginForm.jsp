<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<%

Cookie[] cookies= request.getCookies();
String autoLoging="";
if (cookies != null && cookies.length > 0) {
	for (int i = 0; i < cookies.length; i++) {
		if(cookies[i].getName().equals("autologin")){
			autoLoging = cookies[i].getValue();
			System.out.println(autoLoging);
		}
	}
}

String ms_seq = request.getParameter("ms_seq");
String ms_id = request.getParameter("ms_id");

%>
<section class="content">
	<h2 class="skip">ABOUT</h2>
	<div class="content_area about_area clearfix">
		<div class="login_area">
			<div class="login_inner">
			<h2 class="login_logo">MOVIE SCRAP LOGIN</h2>
			<form action="${PATH }/login.me" method="post" name="loginFrm">
			<fieldset>
				<input type="hidden" name="encPw" />
				<h3>LOGIN</h3>
				<div class="login_input clearfix">
					<ul class="clear">
						<li class="id">
							<label for="login_id" class="skip">회원아이디<strong class="sound_only"> 필수</strong></label>
							<input type="text" name="mb_id" id="login_id" value="<%=autoLoging %>" class="input-text" size="20" maxlength="20" placeholder="아이디">
						</li>
						<li class="pw">
							<label for="login_pw" class="skip">비밀번호<strong class="sound_only"> 필수</strong></label>
							<input type="password" name="mb_pw" id="login_pw" required="" class="input-text" size="20" maxlength="20" placeholder="비밀번호">
						</li>
					</ul>
					
					<input type="hidden" value="<%=ms_seq %>" name="ms_seq" />
					<input type="hidden" value="<%=ms_id %>" name="ms_id" />
					<input type="submit" value="LOGIN" class="btn_login">
				</div>
				<div class="login_save">
					<span class="chk_area"><input type="checkbox" name="auto_login" id="login_auto_login" <%if(autoLoging != "") {%>checked="checked"<%}%>> 계정정보저장</span>
				</div>
				<div class="login_btn_area btn_area_left">
					<a href="${PATH}/MemberIntro.me" class="btn02">회원가입</a>
					<a href="${PATH}/findIdPw.me" class="btn02">아이디/비밀번호 찾기</a>
				</div>
			</fieldset>
			</form>
			</div>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>