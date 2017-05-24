<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			if(value == 0) { // 수정
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			} else if(value == 1) {// 삭제
				if(confirm("정말로 삭제 하시겠습니까?")) {
				location.href="BoardDeleteAction.bo?num=${board.board_num}";

				}
			} else return false;
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
				httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
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
			var userId = '${sessionScope.mb_id}';
			
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
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
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
					<h3 class="board_view_title">${board.board_subject}</h3>
					
					<!-- 게시판 정보 -->
					<div class="board_view_top">
						<span class="info">- Date : ${board.board_date}</span>
						<span class="info">- Writer : ${board.board_id}</span>
						<span class="info">- Hit : ${board.board_count+1}</span>
					</div>

					<!-- 첨부파일 -->
					<c:if test="${!empty board.board_file}">
					<div class="board_view_file">
						<h3 class="tit">첨부파일</h3>
						<ul class="clear">
							<li><a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a></li>
						</ul>
					</div>
					</c:if>
					<!-- board_write -->
					<div class="board_view">
						<div class="view_content">${fn:replace(board.board_content, cn, br)}</div>
					</div>

					<!-- board_write bottom -->
					<div class="clearfix board_view_bottom">
						<div class="button_area_left">
							<c:if test="${sessionScope.mb_id!=null}">								
								<c:if test="${sessionScope.mb_id == board.board_id}">
									<input type="button" value="수정" onclick="doAction(0)" class="btn01">
									<input type="button" value="삭제" onclick="doAction(1)" class="btn01">
									<!-- <input type="button" value="답글" onclick="changeView(1)" class="btn01"> -->
								</c:if>
							</c:if>
						</div>						
						<div class="button_area_right">
							<a href="${PATH }/BoardListAction.bo" class="btn01">목록</a>
						</div>
						<!-- javascript:location.href='BoardListAction.bo?page=${pageNum}' -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<br> <br>
	<!-- 댓글 부분 -->
		<div id="comment" class="comment_area">
			<table border="1" bordercolor="lightgray">
				<!-- 댓글 목록 -->
				<c:if test="${requestScope.commentList != null}">
					<c:forEach var="comment" items="${requestScope.commentList}">
						<tr>
							<!-- 아이디, 작성날짜 -->
							<td width="170" style="padding:10px">
							<c:if test="${comment.comment_level > 1}"> 
							<c:forEach begin="1" end="${comment.comment_level-1 }"><img src="${IMG_PATH }/reply_icon.png" style="width:15px" /></c:forEach>
							</c:if>
							<strong class="id" >${comment.comment_id}</strong><br />
							<c:if test="${comment.comment_level > 1}"> 
							<c:forEach begin="0" end="${comment.comment_level }">&nbsp;&nbsp;</c:forEach>
							</c:if>			
							<span class="date" style="color:gray;font-size:12px;line-height:20px;text-align:center">${comment.comment_date}</span>
							
							</td>
							<!-- 본문내용 -->
							<td width="550"  style="padding:10px;">
								<div class="text_wrapper">
									${fn:replace(comment.comment_content, cn, br)}</div>
							</td>
							<!-- 버튼 -->
							<td width="200" style="padding:10px;">
								<div id="btn" style="text-align:center">
									<a href="#" onclick="cmReplyOpen(${comment.comment_num})" class="btn02">답변</a>
									<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
									<c:if test="${comment.comment_id == sessionScope.mb_id}">
									<a href="#" onclick="cmUpdateOpen(${comment.comment_num})"  class="btn02">수정</a>
									<a href="#" onclick="cmDeleteOpen(${comment.comment_num})"  class="btn02">삭제</a>
									</c:if>
								</div>
							</td>
						</tr>

					</c:forEach>
				</c:if>

				<!-- 로그인 했을 경우만 댓글 작성가능 -->
				<%-- <c:if test="${sessionScope.sessionID !=null}"> --%>
				<c:if test="${sessionScope.mb_id !=null}">
					<tr bgcolor="#F5F5F5">
						<form id="writeCommentForm">
							<input type="hidden" name="comment_board"
								value="${board.board_num}"> <input type="hidden"
								name="comment_id" value="${sessionScope.mb_id}">
							<!-- 아이디-->
							<td width="170" style="text-align:center;padding:10px">
								${sessionScope.mb_id}
							</td>
							<!-- 본문 작성-->
							<td width="550" style="padding:10px;">
								<textarea name="comment_content" rows="4" style="height:100px;background:#fff" cols="70"></textarea>
							</td>
							<!-- 댓글 등록 버튼 -->
							<td width="150" style="text-align:center;">
								<a href="#" onclick="writeCmt()" class="btn01">댓글등록</a>
							</td>
						</form>
					</tr>
				</c:if>
			</table>
		</div> 
		<br><br><br><br><br>
</section>
<c:import url="../layout/footer.jsp"></c:import>