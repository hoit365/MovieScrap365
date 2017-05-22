<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<!-- content -->
<section class="content">
	<script type="text/javascript">
		function checkValue(){
			var form = document.forms[0];
			var board_subject = form.board_subject.value;
			var board_content = form.board_content.value;
			
			if(!board_subject){
				alert("제목을 입력해주세요.")
				return false;
			}
			else if(!board_content){
				alert("내용을 입력해주세요.")
				return false;
			}
		}
	
	</script>
	<div class="content_area">
		<div class="section">
			<h2 class="title01">토론방 작성</h2>
			<form method="post" action="BoardWriteAction.bo" name="boardForm" enctype="multipart/form-data" onsubmit="return checkValue()">
				<div class="board_write_area">
					<!-- 제목 -->
					<div class="clearfix board_write_top">
						<input name="board_subject" type="text" size="70" maxlength="100" value="" />
					</div>
					<!-- //제목 -->
					<!-- 기타설정 -->
					<div class="board_write_chk">
						<ul class="clear list01">
							<li><span class="tit">작성자</span><span class="con"><input type="text" name="board_id" id="board_id" value="${id }" /></span></li>
							<li><span class="tit">비밀번호</span>
								<span class="con">
								<input type="password" name="bo_pass" placeholder="비밀번호" size="500" />
								</span>
							</li>
						</ul>
					</div>
					<!-- //기타설정 -->
				<tr>
					<!-- board_write -->
					<div class="board_write">
						<textarea name="board_content" id="board_content" width="100%" height="300px">${board.board_content}</textarea>
					</div>
					<!-- //board_write -->
					<!-- board_file -->
					<div class="board_file">
						<div class="btn_file"><a href="" class="btn01">첨부파일</a></div>
						<ul class="clear" id="file_list">
							<li><input type="file" name="board_file" /></li>
						</ul>
					</div>
					<!-- //board_file -->
				</div>
				<!-- 버튼 -->
				<div class="clearfix board_write_bottom">
					<div class="button_area_left">
						<a href="${PATH }/BoardListAction.bo" class="btn02">목록</a>
					</div>
					<div class="button_area_right">
						<input type="submit" class="btn01" value="등록" />
					</div>
				</div>
				<!-- //버튼 -->
			</form>
		</div>
	</div>
</section>
<!-- //content -->

<c:import url="../layout/footer.jsp"></c:import>