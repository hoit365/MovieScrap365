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
			<form action="${PATH}/admin_member_update.ad" method="post" name="memberControl" id="frm">
				<input type="hidden" name="search" value="${param.search }" />
				<input type="hidden" name="searchKeyword" value="${param.searchKeyword }" />
				<input type="hidden" name="mb_id" value="${param.mb_id }" />
				<input type="hidden" name="page" value="${param.page }" />
				
				<table border="1">
					<colgroup>
						<col width="120" />
						<col width="400" />
						<col width="120" />
						<col width="400" />
					</colgroup>
					<tr>
						<th>아이디</th>
						<td> &nbsp;${viewBean.MB_ID } <input type="hidden" name="mb_id" width="400" size="80"
							value="${viewBean.MB_ID }" readonly class="updateTable" id="mb_id" /></td>
						<th>이름</th>
						<td><input type="text" name="mb_name" value="${viewBean.MB_NAME }"
							class="updateTable" id="mb_name" /></td>
					</tr>

					<tr>
						<th>이메일</th>
						<td><input type="text" class="updateTable" name="mb_email"
							width="280" value="${viewBean.MB_EMAIL }" size="100" placeholder="이메일을 정확하게 입력해주세요."
							id="mb_email" class="datepicker" />&nbsp;</td>
						<th>생년월일</th>
						<td><input type="text" name="mb_birth" class="updateTable datepicker"
							width="280" size="80" placeholder="생년월일" id="mb_birth" value="${viewBean.MB_BIRTH.substring(0, 10) }" /></td>
					</tr>
					<tr>
						<th>성별</th>
						<td><select name="mb_gender" id="mb_gender"
							class="input-select">
								<option value="male" <c:if test="${viewBean.MB_GENDER == 'male' }">selected</c:if>>남자</option>
								<option value="female" <c:if test="${viewBean.MB_GENDER == 'female' }">selected</c:if>>여자</option>
						</select></td>
						<th>핸드폰번호</th>
						<td><input type="text" class="updateTable" name="mb_ph"
							value="${viewBean.MB_PH }" placeholder="숫자만 입력해주세요." id="mb_ph" /></td>
					</tr>
					<tr>
						<th>회원상태</th>
						<td><select name="mb_stat" class="input-select">
								<option value="active" <c:if test="${viewBean.MB_STAT == 'active' }">selected</c:if> >활성화</option>
								<option value="ban" <c:if test="${viewBean.MB_STAT == 'ban' }">selected</c:if> > 일시정지</option>
								<option value="inactive" <c:if test="${viewBean.MB_STAT == 'inactive' }">selected</c:if> >비활성화</option>
						</select></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<div class="btn_area_right">
				
					<input type="submit" value="정보수정" class="btn01" /> 
				</div>
			</form>
		</div>
		<div class="search_area">
			<form action="${PATH}/search_member_list.ad" method="get" name="searchBox" id="searchBox">
				<select name="search" class="input-select">
					<option value="mb_id"  <c:if test="${param.search == 'mb_id' }">selected</c:if> >아이디</option>
					<option value="mb_name" <c:if test="${param.search == 'mb_name' }">selected</c:if> >이름</option>
					<option value="mb_ph" <c:if test="${param.search == 'mb_ph' }">selected</c:if> >전화번호</option>
					<option value="mb_email" <c:if test="${param.search == 'mb_email' }">selected</c:if> >이메일</option>
					<option value="mb_stat" <c:if test="${param.search == 'mb_stat' }">selected</c:if> >상태</option>
				</select> 
				<input type="text" name="searchKeyword" id="stx"
					placeholder="검색어를 입력하세요" class="serach" value="${param.searchKeyword }"/> 
				<input type="submit"value="검색" class="btn01" /> 
				<a href="${PATH}/member_list.ad" class="btn01">초기화</a>
			</form>
		</div>
		<div class="board_list">
			<table>
				<colgroup>
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
					<c:forEach var="i" items="${resultList }" varStatus="num">
						<tr>
					
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
							<td><a href="member_list.ad?mb_id=${i.MB_ID }&page=${page}&searchKeyword=${param.searchKeyword}&search=${param.search}" id="memView_">관리</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 페이징 처리 -->
		<div class="page_area">
		<c:if test="${page > 1 }">
			<a href="./member_list.ad?stx=${param.stx }&page=${page-1}" class="page_prev">이전</a>
		</c:if>
		<c:forEach begin="1" end="${endpage }" var="p">
		<c:choose>
		<c:when test="${p == page }">
			<em>${p }</em>
		</c:when>
		<c:otherwise>
		<a href="./member_list.ad?stx=${param.stx }&page=${p}">${p }</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<c:if test="${page < endpage }">
			<a href="./member_list.ad?stx=${param.stx }&page=${page+1}" class="page_next">다음</a>
		</c:if>	
		</div>
	</div>

</div>
<c:import url="../layout/footer.jsp"></c:import>