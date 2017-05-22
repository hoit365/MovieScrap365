<%@page import="com.sun.glass.ui.Window"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<jsp:useBean id="dao" class="net.member.db.MemberDAO"></jsp:useBean>
<jsp:useBean id="member" class="net.member.db.MemberBean"></jsp:useBean>
<script type="text/javascript">
	//회원정보 불러오기
	
<%member = (MemberBean) request.getAttribute("member");
 session.getAttribute("mb_id");
			%>
	
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function frmUpdateSubmit(frm){
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

	if( f.mb_email.value == '' ){
		alert("이메일을 입력해주세요.");
		return false;
	}
	
	var regExg=/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	if( ! regExg.test(f.mb_email.value) ){
		alert("올바른 이메일 형식이 아닙니다.");
		return false;
	}
	return true;
}


	
</script>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<h2 class="title01">회원정보 수정</h2>
			<form action="${PATH}/MemberInfoUpdate.me" method="post"
				onSubmit="return frmUpdateSubmit(this);" name="joinForm">
				<input type="hidden" value="0" name="id_chk" />
				<table class="tbl_join">
					<colgroup>
						<col class="col1" />
						<col class="col2" />
					</colgroup>

					<tr>
						<th>이름</th>
						<td><input type="hidden" name="mb_name"
							value="<%=member.getMB_NAME()%>" /> <label><%=member.getMB_NAME()%></label>
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
						<th>아이디</th>
						<td><input type="hidden" name="id" size="80" value="" />
							<label><%=member.getMB_ID()%></label></td>
					</tr>
					
					<tr>
						<th>이메일</th>
						<td><input type="text" name="mb_email"
							value="<%=member.getMB_EMAIL()%>" size="100" />&nbsp; <label
							for="mb_mailing"><input type="checkbox" id="mb_mailing"
								name="mb_mailing" value="1" /> </label>메일링여부</td>
					</tr>
					<tr>
					<tr>
						<th>생년월일</th>
						<td><input type="hidden" name="mb_birth" size="80"
							value="<%=member.getMB_BIRTH().substring(0, 10)%>
						"
							placeholder="생년월일" /> <label><%=member.getMB_BIRTH().substring(0, 10)%></label>
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td><label><input type="hidden" name="mb_gender"
								value="<%=member.getMB_GENDER()%>" checked="checked" /> 
								<%
 									if (member.getMB_GENDER().equals("male")) {
 											%>남자<%
								 	} else {
											 %>여자 <%
										 	}%> 
							</label></td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td><input type="text" name="mb_ph" value="<%=member.getMB_PH() %>"
							placeholder="숫자만 입력해주세요." /></td>
					</tr>
				</table>
				<div class="btn_area_right">
					<input type="submit" value="회원정보 수정하기" class="btn01" />
					<a href="${PATH}/main/main.jsp" class="btn02">홈으로 돌아가기</a>
				</div>
			</form>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>