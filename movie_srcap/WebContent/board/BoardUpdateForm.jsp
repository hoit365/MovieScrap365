<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../layout/header.jsp"></c:import>
<%
	// 줄바꿈 
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>

<script type="text/javascript">
	function changeView() {
		location.href = 'BoardListAction.bo?page=${pageNum}';
	}
</script>

<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section">
			<h2 class="title01">토론방 글수정</h2>
			<form method="post" action="BoardUpdateAction.bo?page=${pageNum}" name="boardForm" enctype="multipart/form-data">
				<div class="board_write_area">
					<input type="hidden" name="board_num" value="${board.board_num}"/>
					<input type="hidden" name="existing_file" value="${board.board_file}" />
					<!-- 제목 -->
					<div class="clearfix board_write_top">
						<input name="board_subject" type="text" size="70" maxlength="100" value="${board.board_subject}" />
					</div>
					<!-- //제목 -->
					<!-- 기타설정 -->
					<div class="board_write_chk">
						<ul class="clear list01">
							<li><span class="tit">작성자</span> <span class="con">${id}</span>
							</li>
							<li><span class="tit">비밀번호</span>
								<span class="con">
									<input type="password" name="bo_pass" placeholder="비밀번호" size="500" />
								</span>
							</li>
						</ul>
					</div>
					<!-- //기타설정 -->
					
					<!-- 첨부파일 -->
					<div class="board_view_file">
						<h3 class="tit">첨부파일</h3>
						<ul class="clear">
							<li><a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a></li>
						</ul>
					</div>
					<div class="board_write">
						<!-- board_write -->
						<textarea name="bo_content" id="bo_content" width="100%" height="300px">${board.board_content}</textarea>
					</div>
					<!-- board_file -->
					<div class="board_file">
						<div class="btn_file">
							<a href="" class="btn01">첨부파일</a>
						</div>
						<ul class="clear" id="file_list">
							<li><input type="file" name="board_file" /></li>
						</ul>
					</div>
					<!-- //board_file -->
				</div>
				<!-- 버튼 -->
				<div class="clearfix board_write_bottom">
					<div class="button_area_left">
					<input type="reset" value="작성취소" class="btn02"/>
					<a href="${PATH }/BoardListAction.bo" class="btn02">목록</a>
					<!-- <input type="button" class="btn02" value="목록" onclick="changeView()" /> -->
						<%-- <a href="${PATH }/BoardListAction.bo" class="btn02">작성취소</a> --%>
					</div>
					<div class="button_area_right">
						<input type="submit" class="btn01" value="수정" />
					</div>
				</div>
			</form>
		</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>