<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<jsp:useBean id="dao" class="net.member.db.MemberDAO"></jsp:useBean>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
<%
int dupChkFlag = 0;
%>
	
	function frmJoinSubmit(joinForm){
		var f = joinForm;
		
		if( f.mb_name.value == '' ){
			alert("이름을 입력하세요");
			return false;
		}
		
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

		if( f.mb_email.value == '' ){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		var regExg=/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if( ! regExg.test(f.mb_email.value) ){
			alert("올바른 이메일 형식이 아닙니다.");
			return false;
		}
		
		if( f.mb_id_chk.value == '0' ){
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		
		return true;
	}
	
	function idDupChk(dupChk){
		var f = joinForm;
		//아이디값 중복체크 
		var id_chk = f.mb_id.value;
		console.log("호출완료");
		console.log(f.id_chk);
		if( id_chk != ""){
			$.ajax({
				url : "./member/id_check.jsp?mb_id="+f.mb_id.value
			}).done(function(data){
				if(data.trim() == "false"){
					alert("["+f.mb_id.value +"] 이미 사용하고 있는 아이디입니다.");
					f.mb_id_chk.value = '0';
				}else{
					alert("사용할수 있는 아이디입니다.");
					f.mb_id_chk.value = '1';
				}
			});	
		}else{
			alert("아이디를 입력해주세요.");
			document.f.mb_id.focus();
		}
	}
</script>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<h2 class="title01">회원가입</h2>
			<form action="${PATH}/MemberJoinAction.me" method="post" onSubmit="return frmJoinSubmit(this);" name="joinForm" id="frm">
				<input type="hidden" value="0" name="mb_id_chk" />
				<table class="tbl_join">
					<colgroup>
						<col class="col1" />
						<col class="col2" />
					</colgroup>
					<tr>
						<th>이름</th>
						<td><input type="text" name="mb_name" value="" /></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="mb_id" size="80" value="" /> 
						<a href="#" id = "dupChk" class="btn01" onclick="return idDupChk(this);">아이디중복확인</a>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="mb_pw" size="100" /></td>
					</tr>
					<tr>
						<th>비밀번호 재입력</th>
						<td><input type="password" name="mb_pw_re" size="100" /></td>
					</tr>								
					<tr>
						<th>이메일</th>
						<td>
						<input type="text" name="mb_email" value="" size="100" placeholder="이메일을 정확하게 입력해주세요." />&nbsp; 
						<label for="mb_mailing"><input type="checkbox" id="mb_mailing" name="mb_mailing" value="1" />
						</label>메일링여부
						</td>
					</tr>
					<tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" name="mb_birth" size="80" class="datepicker" placeholder="생년월일" /></td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<label><input type="radio" name="mb_gender" value="male" checked="checked"/> 남</label> &nbsp;&nbsp;
							<label><input type="radio" name="mb_gender" value="female" /> 여</label>
						</td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td><input type="text" name="mb_ph" value="${mVo.mb_ph }"
							placeholder="숫자만 입력해주세요." /></td>
					</tr>
				</table>
				<div class="btn_area_right">
					<input type="submit" value="회원가입" class="btn01" />
					<a href="${PATH}/main/main.jsp" class="btn02">취소</a>
				</div>
			</form>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>