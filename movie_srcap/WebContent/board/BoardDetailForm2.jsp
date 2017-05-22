<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../layout/header.jsp"></c:import>
<%
	// 줄바꿈 
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>

<script type="text/javascript">
		function changeView(value)
		{
			if(value == 0)	
				location.href='BoardListAction.bo?page=${pageNum}';
			else if(value == 1)
				location.href='BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}';
		}
		
		function doAction(value)
		{
			if(value == 0) // 수정
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			else if(value == 1) // 삭제
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
		

		var httpRequest = null;
		
		// httpRequest 객체 생성
		function getXMLHttpRequest(){
			var httpRequest = null;
		
			if(window.ActiveXObject){
				try{
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
				} catch(e) {
					try{
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) { httpRequest = null; }
				}
			}
			else if(window.XMLHttpRequest){
				httpRequest = new window.XMLHttpRequest();
			}
			return httpRequest;	
		}
		
		// 댓글 등록
		function writeCmt()
		{
			var form = document.getElementById("writeCommentForm");
			
			var board = form.comment_board.value
			var id = form.comment_id.value
			var content = form.comment_content.value;
			
			if(!content)
			{
				alert("내용을 입력하세요.");
				return false;
			}
			else
			{	
				var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
					
				httpRequest = getXMLHttpRequest();
				httpRequest.onreadystatechange = checkFunc;
				httpRequest.open("POST", "CommentWriteAction.co", true);	
				httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
				httpRequest.send(param);
			}
		}
		
		function checkFunc(){
			if(httpRequest.readyState == 4){
				// 결과값을 가져온다.
				var resultText = httpRequest.responseText;
				if(resultText == 1){ 
					document.location.reload(); // 상세보기 창 새로고침
				}
			}
		}
		
		// 댓글 답변창
		function cmReplyOpen(comment_num){
			var userId = '${sessionScope.sessionID}';
			
			if(userId == "" || userId == null){
				alert("로그인후 사용가능합니다.");
				return false;
			}
			else{
				// 댓글 답변창 open
				window.name = "parentForm";
				window.open("CommentReplyFormAction.co?num="+comment_num,
							"replyForm", "width=570, height=350, resizable = no, scrollbars = no");
			}
		}
		
		// 댓글 삭제창
		function cmDeleteOpen(comment_num){
			var msg = confirm("댓글을 삭제합니다.");	
			if(msg == true){ // 확인을 누를경우
				deleteCmt(comment_num);
			}
			else{
				return false; // 삭제취소
			}
		}
	
		// 댓글 삭제
		function deleteCmt(comment_num)
		{
			var param="comment_num="+comment_num;
			
			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentDeleteAction.co", true);	
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
			httpRequest.send(param);
		}
		
		// 댓글 수정창
		function cmUpdateOpen(comment_num){
			window.name = "parentForm";
			window.open("CommentUpdateFormAction.co?num="+comment_num,
						"updateForm", "width=570, height=350, resizable = no, scrollbars = no");
		}
		
</script>

<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section">
			<h2 class="title01">토론방</h2>
			<div class="board_view_area">
				<form method="post" name="frm">
					<h3 class="board_view_title">제목제목제목1</h3>
					<!-- 게시판정보 -->
					<div class="board_view_top">
						<span class="info">- Date : 2013-11-23 </span>
						<span class="info">- Writer : 김남형</span>
						<span class="info">- Hit : 3</span>
					</div>
					<!-- //게시판정보 -->
					<!-- 첨부파일 -->
					<div class="board_view_file">
						<h3 class="tit">첨부파일</h3>
						<ul class="clear">
							<li><a href="">첨부파일1111</a></li>
						</ul>
					</div>
					<!-- //첨부파일 -->
					<!-- board_write -->
					<div class="board_view">
						<div class="view_content">내용내용</div>
					</div>
					<!-- //board_write -->
					<!-- board_write_bottom -->
					<div class="clearfix board_view_bottom">
						<div class="button_area_left">
							<a href="" class="btn02">수정</a>
							<a href="" class="btn02">답변</a>
						</div>
						<div class="button_area_right">
							<a href="" class="btn01">목록</a>
						</div>
					</div>
					<!-- //board_write_bottom -->
				</form>
			</div>
		</div>
	</div>
</section>
<!-- //content -->

<c:import url="../layout/footer.jsp"></c:import>