<%@page import="java.util.List"%>
<%@page import="net.admin.db.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<%
	List member = (List)request.getAttribute("memberList");
%>
<div class="content" id="content">
	<div class="content_top">
		<h2>멤버관리</h2>
		<p>회원을 관리할 수 있습니다.</p>
	</div>
	<div class="content_inner">
			<div class="section">
			<form action="${PATH}" style ="border: 3px; border-color: red;" method="post" name="memberControl" id="frm">
				<input type="hidden" value="0" name="mb_id_chk" />
				<table border="1">
				<colgroup>
					<col width="120" />
					<col width="400" />
					<col width="120" />
					<col width="400" />
				</colgroup>
					<tr>
						<th>이름</th>
						<td><input type="text" name="mb_name" value="" /></td>
					
						<th>아이디</th>
						<td><input type="text" name="mb_id" width="400" size="80" value="" /></td>
					</tr>
													
					<tr>
						<th>이메일</th>
						<td>
						<input type="text" name="mb_email" width="280" value="" size="100" placeholder="이메일을 정확하게 입력해주세요." />&nbsp; 
						</td>
						<th>생년월일</th>
						<td><input type="text" name="mb_birth" width="280" size="80" class="datepicker" placeholder="생년월일" /></td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<label><input type="radio" name="mb_gender" value="male" checked="checked"/> 남</label> &nbsp;&nbsp;
							<label><input type="radio" name="mb_gender" value="female" /> 여</label>
						</td>
						<th>핸드폰번호</th>
						<td><input type="text" name="mb_ph" value="${mVo.mb_ph }"
							placeholder="숫자만 입력해주세요." /></td>
					</tr>
				</table>
				<div align="right">
				
					<a href="${PATH}/main/main.jsp" class="btn01">비밀번호리셋</a>
					
					<a href="${PATH}/main/main.jsp" class="btn01">회원정지</a>
					<input type="submit" value="회원가입" class="btn01" />
					<a href="${PATH}/main/main.jsp" class="btn01">취소</a>
					</div>
					</form>
				</div>
		<div class="search_area">
			<form action="memberSearch.ad"method="post" name="searchBox" id="searchBox">
				<select name="search" class="input-select">
					<option value="mb_id">아이디</option>
					<option value="mb_name">이름</option>
					<option value="mb_ph">전화번호</option>
					<option value="mb_email">이메일</option>
					<option value="mb_stat">상태</option>
				</select> <input type="text" name="searchKeyword" id="stx"
					placeholder="검색어를 입력하세요" /> <input type="submit" value="검색"
					class="btn01" />
			</form>
		</div>
		<div class="board_list">
			<table>
				<colgroup>
					<col width="50" />
					<col width="120" />
					<col width="80" />
					<col width="90" />
					<col width="120" />
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
				<tbody>
					<%
						for (int i = 0; i < member.size(); i++) {
							AdminBean ml = (AdminBean) member.get(i);
					%>
					<tr>
						<td><%=ml.getNum()%></td>
						<td><%=ml.getMB_ID() %></td>
						<td><%=ml.getMB_NAME() %></td>
						<td><%=ml.getMB_BIRTH().substring(0, 10) %></td>
						<td><%=ml.getMB_GENDER() %></td>
						<td><%=ml.getMB_PH() %></td>
						<td><%=ml.getMB_EMAIL() %></td>
						<td><%=ml.getMB_REGDATE().substring(0, 10) %></td>
						<td><%=ml.getMB_STAT() %></td>
						<td><a href="#">관리</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<div align="center">
		<a href="#"class="btn01">1</a>
		<a href="#"class="btn01">2</a>
		<a href="#"class="btn01">3</a>
		<a href="#"class="btn01">4</a>
		<a href="#"class="btn01">5</a>
		<a href="#"class="btn01">6</a>
		<a href="#"class="btn01">7</a>
		<a href="#"class="btn01">8</a>
		<a href="#"class="btn01">9</a>
		<a href="#"class="btn01">10</a>
		</div>
	</div>
</div>
<c:import url="../layout/footer.jsp"></c:import>