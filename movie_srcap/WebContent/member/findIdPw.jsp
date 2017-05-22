<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<section class="content">
	<h2 class="skip">ABOUT</h2>
	<div class="content_area clearfix">
		<!-- find_area -->
		<div class="login_area find_area">
			<h2 class="find_tit">MOVIE SCRAP 아이디/비밀번호찾기</h2>
			
			<div class="clearfix">
				<!-- 아이디찾기 -->
				<div class="login_inner find_inner">
				<form action="${PATH}/findIdFin.me" method="post" name="findIdFrm">
				<fieldset>
					<input type="hidden" name="encPw" />
					<h3>아이디찾기</h3>
					<div class="login_input clearfix">
						<ul class="clear">
							<li class="id">
								<label for="login_id" class="skip">이름<strong class="sound_only"> 필수</strong></label>
								<input type="text" name="mb_name" id="mb_name" required="" class="input-text" size="20" maxlength="20" placeholder="이름">
							</li>
							<li class="pw">
								<label for="login_pw" class="skip">전화번호<strong class="sound_only"> 필수</strong></label>
								<input type="text" name="mb_ph" id="mb_ph" required="" class="input-text" size="20" maxlength="20" placeholder="전화번호">
							</li>
						</ul>
						<input type="submit" value="SEARCH" id = "findId" class="btn_login">
					</div>
				</fieldset>
				</form>
				</div>
				<!-- //아이디찾기 -->
				<!-- 비밀번호찾기 -->
				<div class="login_inner find_inner">
				<form action="${PATH}/findPwFin.me" method="post" name="findPwFrm">
				<fieldset>
					<input type="hidden" name="encPw" />
					<h3>비밀번호찾기</h3>
					<div class="login_input clearfix">
						<ul class="clear">
							<li class="id">
								<label for="login_id" class="skip">ID<strong class="sound_only"> 필수</strong></label>
								<input type="text" name="mb_id" id="mb_id" required="" class="input-text" size="20" maxlength="20" placeholder="ID">
							</li>
							<li class="pw">
								<label for="login_pw" class="skip">전화번호<strong class="sound_only"> 필수</strong></label>
								<input type="text" name="mb_ph" id="mb_ph" required="" class="input-text" size="20" maxlength="20" placeholder="전화번호">
							</li>
						</ul>
						<input type="submit" value="SEARCH" id = "findPw"class="btn_login">
					</div>
				</fieldset>
				</form>
				</div>
				<!-- //비밀번호찾기 -->
			</div>
			<div class="btn_area_center find_btn_area">
				<a href="${PATH }/member/joinIntro.jsp" class="btn02">회원가입</a>
				<a href="${PATH }/member/loginForm.jsp" class="btn02">로그인</a>
				<a href="${PATH }/main/main.jsp" class="btn02">메인으로</a>
			</div>
		</div>
		<!-- //find_area -->
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>