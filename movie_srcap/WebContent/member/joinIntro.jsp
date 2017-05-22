<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section join_intro_area">
		<h2 class="title01">JOIN</h2>
		<form action="${PATH}/memberJoin.me" method="post" onSubmit="return frmSubmit(this);">
			<h3 class="title02">개인정보취급방침</h3>
			<div class="textarea_wrap">
			<textarea name="cf_privacy">${configVo.cf_privacy }</textarea>
			</div>
			<label><input type="checkbox" name="chk_privacy" /></label> &nbsp;개인정보취급방침에 동의합니다.
			
			<h3 class="title02">이용약관</h3>
			<div class="textarea_wrap">
			<textarea name="cf_service">${configVo.cf_service }</textarea>
			</div>
			<label><input type="checkbox" name="chk_service" /></label> &nbsp;이용약관에 동의합니다.</span>
			<div class="btn_area_right">
			<input type="submit" value="다음" class="btn01" />
			</div>
		</form>	
		</div>
	</div>
</section>
<!-- //content -->
<script>
	function frmSubmit(frm){
		var f = frm;
		if( !f.chk_privacy.checked ){
			alert("개인정보방침에 동의해주세요");
			return false;
		}
		if( !f.chk_service.checked ){
			alert("이용약관에 동의해주세요");
			return false;
		}
		
		return true;
	}
</script>
<c:import url="../layout/footer.jsp"></c:import>