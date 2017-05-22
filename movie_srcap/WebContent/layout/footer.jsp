<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	</div><!-- //container -->
</div><!-- //container_wrap -->
<!-- } 콘텐츠 끝 -->
<hr />
<!-- 하단 시작 { -->
<div class="footer_wrap"><!-- footer_wrap -->
	<div class="footer" id="footer"> <!-- footer -->
		<!-- footer_menu -->
		<div class="footer_menu">
			<h2 class="skip">하단메뉴</h2>
			<ul class="clear clearfix">
				<?php setMenuList(getMenuList(1)); ?>
			</ul>
		</div>
		<!-- //footer_menu -->
		<!-- bottom_global_menu -->
		<div class="bottom_global_menu">
			<h3 class="skip">글로벌 하단메뉴</h3>
			<ul class="clear clearfix">
				<li><a href="/bbs/content.php?co_id=provision">서비스이용약관</a></li>
				<li class="on"><a href="/bbs/content.php?co_id=privacy">개인정보취급방침</a></li>
				<?php if (!$is_member) {  ?>
				<li><a href="<?php echo G5_BBS_URL ?>/register.php">회원가입</a></li>
				<li><a href="<?php echo G5_BBS_URL ?>/login.php">로그인</a></li>
				<?php } ?>
			</ul>
		</div>
		<!-- //bottom_global_menu -->
		
		<div class="footer_bottom clearfix">
			<!-- footer_logo_area -->
			<div class="footer_logo_area">
				<img src="${IMG_PATH }/common/footer_logo.png" alt="">
			</div>
			<!-- //footer_logo_area -->
			<!-- address_area -->
			<div class="address_area">
				<address>
					<p>
					<span></span>
					<span>Soldesk</span>
					<span>E-mail : soldesk@naver.com</span>
					<span>TEL : 010-1234-1224</span>
					</p>
					<p class="copyright">COPYRIGHT &copy;2017 ALL RIGHTS RESERVED. BY <?=$config['cf_title'] ?></p>
				</address>
			</div>
			<!-- //address_area -->
		</div>

		<a href="#header" class="btn_top">위로</a>
	</div><!-- //footer -->
</div><!-- //footer_wrap -->

<!-- 레이어팝업 -->
<div class="layer_pop_area wide">
	<!-- 전체메뉴 -->
	<div class="all_menu" id="all_menu">
	</div>
	<!-- //전체메뉴 -->
</div>
<!-- //레이어팝업 -->
<div class="blind"></div>

<c:import url="${URL }/layout/footer_sub.jsp"></c:import>