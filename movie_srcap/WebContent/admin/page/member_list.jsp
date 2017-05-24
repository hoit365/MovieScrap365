<%@page import="java.util.List"%>
<%@page import="net.admin.db.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<%
	List member = (List) request.getAttribute("memberList");
	AdminBean ml = new AdminBean();
%>

<div class="content" id="content">
	<div class="content_top">
		<h2>멤버관리</h2>
		<p>회원을 관리할 수 있습니다.</p>
	</div>
	<div class="content_inner">
		<div class="section">
			<form action="${PATH}/member_list.ad"
				style="border: 3px; border-color: red;" method="post"
				name="memberControl" id="frm">
				<table border="1">
					<colgroup>
						<col width="120" />
						<col width="400" />
						<col width="120" />
						<col width="400" />
					</colgroup>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="mb_id" width="400" size="80"
							value="" class="updateTable" id="mb_id" /></td>
						<th>이름</th>
						<td><input type="text" name="mb_name" value=""
							class="updateTable" id="mb_name" /></td>
					</tr>

					<tr>
						<th>이메일</th>
						<td><input type="text" class="updateTable" name="mb_email"
							width="280" value="" size="100" placeholder="이메일을 정확하게 입력해주세요."
							id="mb_email" />&nbsp;</td>
						<th>생년월일</th>
						<td><input type="text" class="updateTable" name="mb_birth"
							width="280" size="80" placeholder="생년월일" id="mb_birth" /></td>
					</tr>
					<tr>
						<th>성별</th>
						<td><select name="mb_gender" id="mb_gender"
							class="input-select">
								<option value="male">남자</option>
								<option value="female">여자</option>
						</select></td>
						<th>핸드폰번호</th>
						<td><input type="text" class="updateTable" name="mb_ph"
							value="" placeholder="숫자만 입력해주세요." id="mb_ph" /></td>
					</tr>
					<tr>
						<th>회원상태</th>
						<td><select name="mb_stat" class="input-select">
								<option value="active">활성화</option>
								<option value="ban">일시정지</option>
								<option value="inactive">비활성화</option>
						</select></td>
						<td></td>
						<td></td>

					</tr>
				</table>
				<div align="right">

					<a href="${PATH}/main/main.jsp" class="btn01">비밀번호리셋</a> <input
						type="submit" value="정보수정" class="btn01" /> <a
						href="${PATH}/main/main.jsp" class="btn01">취소</a>
				</div>
			</form>
		</div>
		<div class="search_area">
			<form action="${PATH}/search_member_list.ad" method="post"
				name="searchBox" id="searchBox">
				<select name="search" class="input-select">
					<option value="mb_id">아이디</option>
					<option value="mb_name">이름</option>
					<option value="mb_ph">전화번호</option>
					<option value="mb_email">이메일</option>
					<option value="mb_stat">상태</option>
				</select> <input type="text" name="searchKeyword" id="stx"
					placeholder="검색어를 입력하세요" class="serach" /> <input type="submit"
					value="검색" class="btn01" /> <a href="${PATH}/member_list.ad"
					class="btn01">초기화</a>
			</form>
		</div>
		<div class="board_list">
			<table>
				<colgroup>
					<col width="50" />
					<col width="120" />
					<col width="80" />
					<col width="110" />
					<col width="80" />
					<col width="120" />
					<col width="200" />
					<col width="200" />
					<col width="150" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>생일</th>
						<th>성별</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>가입일</th>
						<th>상태</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="memberTable">
					<c:forEach var="i" items="${resultList }">
						<tr>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="num" value="${i.NUM }"
								id="MT_${i.NUM }_1"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_id" value="${i.MB_ID }"
								id="MT_${i.NUM }_2"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_name" value="${i.MB_NAME }"
								id="MT_${i.NUM }_3"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_birth"
								value="${i.MB_BIRTH.substring(0, 10) }" id="MT_${i.NUM }_4"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_gender" value="${i.MB_GENDER }"
								id="MT_${i.NUM }_5"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_ph" value="${i.MB_PH }"
								id="MT_${i.NUM }_6"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_email" value="${i.MB_EMAIL }"
								id="MT_${i.NUM }_7"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_regdate"
								value="${i.MB_REGDATE.substring(0, 10) }" id="MT_${i.NUM }_8"></td>
							<td><input type="text" readonly="readonly" class="memTable"
								disabled="disabled" name="mb_stat" value="${i.MB_STAT }"
								id="MT_${i.NUM }_9"></td>
							<td><a href="#" id="memView_">관리</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="pageForm" class="page_area">
			<c:choose>
				<c:when test="${page > 1 }">
					<a href='member_list.ad?&page=${page}' class="page_prev">이전</a>
				</c:when>
				<c:otherwise>
					<a href='#' class="page_prev">이전</a>
				</c:otherwise>
			</c:choose>

			<c:forEach var="pageNum" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${pageNum == page }">
						<em>${pageNum+1 }</em>
					</c:when>
					<c:otherwise>
						<a href='member_list.ad?&page=${pageNum }'>${pageNum }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${page < endPage }">
					<a href='member_list.ad?&page=${page+1 }' class="page_next">다음</a>
				</c:when>
				<c:otherwise>
					<a href='#' class="page_next">다음</a>
				</c:otherwise>
			</c:choose>

		</div>

	</div>
</div>
<script type="text/javascript">
	if (document.getElementById('memView_0') != null) {
		var mv_1 = document.getElementById('memView_0');
		mv_1.addEventListener('click', function() {
			mb_id.value = MT_0_2.value;
			mb_name.value = MT_0_3.value;
			mb_email.value = MT_0_7.value;
			mb_birth.value = MT_0_4.value;
			mb_gender.value = MT_0_5.value;
			mb_ph.value = MT_0_6.value;
			mb_stat.value = MT_0_9.value;
		})
	}
	if (document.getElementById('memView_1') != null) {
		var mv_2 = document.getElementById('memView_1');
		mv_2.addEventListener('click', function() {
			mb_id.value = MT_1_2.value;
			mb_name.value = MT_1_3.value;
			mb_email.value = MT_1_7.value;
			mb_birth.value = MT_1_4.value;
			mb_gender.value = MT_1_5.value;
			mb_ph.value = MT_1_6.value;
			mb_stat.value = MT_1_9.value;
		})
	}
	if (document.getElementById('memView_2') != null) {
		var mv_3 = document.getElementById('memView_2');
		mv_3.addEventListener('click', function() {
			mb_id.value = MT_2_2.value;
			mb_name.value = MT_2_3.value;
			mb_email.value = MT_2_7.value;
			mb_birth.value = MT_2_4.value;
			mb_gender.value = MT_2_5.value;
			mb_ph.value = MT_2_6.value;
			mb_stat.value = MT_2_9.value;
		})
	}
	if (document.getElementById('memView_3') != null) {
		var mv_4 = document.getElementById('memView_3');
		mv_4.addEventListener('click', function() {
			mb_id.value = MT_3_2.value;
			mb_name.value = MT_3_3.value;
			mb_email.value = MT_3_7.value;
			mb_birth.value = MT_3_4.value;
			mb_gender.value = MT_3_5.value;
			mb_ph.value = MT_3_6.value;
			mb_stat.value = MT_3_9.value;
		})
	}
	if (document.getElementById('memView_4') != null) {
		var mv_5 = document.getElementById('memView_4');
		mv_5.addEventListener('click', function() {
			mb_id.value = MT_4_2.value;
			mb_name.value = MT_4_3.value;
			mb_email.value = MT_4_7.value;
			mb_birth.value = MT_4_4.value;
			mb_gender.value = MT_4_5.value;
			mb_ph.value = MT_4_6.value;
			mb_stat.value = MT_4_9.value;
		})
	}
	if (document.getElementById('memView_5') != null) {
		var mv_6 = document.getElementById('memView_5');

		mv_6.addEventListener('click', function() {
			mb_id.value = MT_5_2.value;
			mb_name.value = MT_5_3.value;
			mb_email.value = MT_5_7.value;
			mb_birth.value = MT_5_4.value;
			mb_gender.value = MT_5_5.value;
			mb_ph.value = MT_5_6.value;
			mb_stat.value = MT_5_9.value;
		})
	}
	if (document.getElementById('memView_6') != null) {
		var mv_7 = document.getElementById('memView_6');
		mv_7.addEventListener('click', function() {
			mb_id.value = MT_6_2.value;
			mb_name.value = MT_6_3.value;
			mb_email.value = MT_6_7.value;
			mb_birth.value = MT_6_4.value;
			mb_gender.value = MT_6_5.value;
			mb_ph.value = MT_6_6.value;
			mb_stat.value = MT_6_9.value;
		})
	}
	if (document.getElementById('memView_7') != null) {
		var mv_8 = document.getElementById('memView_7');
		mv_8.addEventListener('click', function() {
			mb_id.value = MT_7_2.value;
			mb_name.value = MT_7_3.value;
			mb_email.value = MT_7_7.value;
			mb_birth.value = MT_7_4.value;
			mb_gender.value = MT_7_5.value;
			mb_ph.value = MT_7_6.value;
			mb_stat.value = MT_7_9.value;
		})
	}
	if (document.getElementById('memView_8') != null) {
		var mv_9 = document.getElementById('memView_8');
		mv_9.addEventListener('click', function() {
			mb_id.value = MT_8_2.value;
			mb_name.value = MT_8_3.value;
			mb_email.value = MT_8_7.value;
			mb_birth.value = MT_8_4.value;
			mb_gender.value = MT_8_5.value;
			mb_ph.value = MT_8_6.value;
			mb_stat.value = MT_8_9.value;
		})
	}
	if (document.getElementById('memView_9') != null) {
		var mv_10 = document.getElementById('memView_9');
		mv_10.addEventListener('click', function() {
			mb_id.value = MT_9_2.value;
			mb_name.value = MT_9_3.value;
			mb_email.value = MT_9_7.value;
			mb_birth.value = MT_9_4.value;
			mb_gender.value = MT_9_5.value;
			mb_ph.value = MT_9_6.value;
			mb_stat.value = MT_9_9.value;
		})
	}
</script>
<c:import url="../layout/footer.jsp"></c:import>