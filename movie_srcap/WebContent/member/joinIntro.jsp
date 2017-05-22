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
			<textarea name="cf_privacy">MovieScrap 개인정보취급방침</textarea>
			</div>
			<label><input type="checkbox" name="chk_privacy" /></label> &nbsp;개인정보취급방침에 동의합니다.
			
			<h3 class="title02">이용약관</h3>
			<div class="textarea_wrap">
			<textarea name="cf_service">MovieScrap은 MovieScrap 서비스 사용자들이 신뢰를 바탕으로 정보를 제공한다는 것을 잘 알고 있습니다. 본 개인정보처리방침은 Google에서 수집하는 데이터의 유형, 데이터를 수집하는 이유, 데이터의 용도에 대한 이해를 돕기 위한 것입니다. 개인정보처리방침은 중요한 내용이므로 주의 깊게 읽어보시기 바랍니다. 또한 내 계정에서 사용자의 정보를 관리하고, 개인정보 및 보안을 강화하기 위한 제어 기능을 확인할 수 있습니다.
			</textarea>
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