<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<script type="text/javascript">
<% 
if((String[])request.getAttribute("msgPw")!= null){
String[] msgPw ;
msgPw = new String[2];
msgPw = (String[])request.getAttribute("msgPw");
}
MemberBean member = (MemberBean)request.getAttribute("member");
String mb_id = member.getMB_ID();
System.out.println("fin"+mb_id);
%>
function frmResetSubmit(frm){
	var f = frm;
	
	if( f.mb_pw.value == '' ){
		alert("비밀번호를 입력하세요");
		return false;
	}
	
	if( f.mb_pw_re.value == '' ){
		alert("비밀번호를 재입력하세요.");
		return false;
	}
	
	if( f.mb_pw.value != f.mb_pw_re.value ){
		alert("비밀번호가 동일하지 않습니다.");
		return false;
	}

	return true;
}

</script>

<!-- content -->
<section class="content">
	<div class="content_area joinFin_area clearfix">
		<div class="section">
		<%if((String[])request.getAttribute("msgPw")!= null){%>
			<h2 class="title01"> ${msgPw[0] } </h2>
			<center><p>${msgPw[1] }</p></center><%}
			 %>
		<div class="login_area">
			<div class="login_inner">
			<form action="${PATH }/passwordReset.me" method="post" onSubmit="return frmResetSubmit(this);" name="loginFrm">
			<fieldset>
				<input type="hidden" name="mb_id" id="mb_id" value="<%=mb_id %>" />
				<div class="login_input clearfix">
					<ul class="clear">
						<li class="id">
							<label for="login_id" class="skip">비밀번호<strong class="sound_only"> 필수</strong></label>
							<input type="text" name="mb_pw"  required="" class="input-text" size="20" maxlength="20" placeholder="비밀번호">
						</li>
						<li class="pw">
							<label for="login_pw" class="skip">비밀번호 재입력<strong class="sound_only"> 필수</strong></label>
							<input type="password" name="mb_pw_re"  required="" class="input-text" size="20" maxlength="20" placeholder="비밀번호 재입력">
						</li>
					</ul>
					<input type="submit" value="비밀번호 재설정" class="btn_login">
				</div>
				<div class="login_save">
					<a href="${PATH}/findIdPw.me" class="btn02">잠깐! 비밀번호가 기억났어요 로그인할래요</a>
				</div>
					
			</fieldset>
			</form>
			</div>
		</div>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>