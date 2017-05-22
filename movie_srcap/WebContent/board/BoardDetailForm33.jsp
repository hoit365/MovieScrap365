<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="../layout/header.jsp"></c:import>
<%
	// �ٹٲ� 
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
			if(value == 0) // ����
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			else if(value == 1) // ����
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
		

		var httpRequest = null;
		
		// httpRequest ��ü ����
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
		
		// ��� ���
		function writeCmt()
		{
			var form = document.getElementById("writeCommentForm");
			
			var board = form.comment_board.value
			var id = form.comment_id.value
			var content = form.comment_content.value;
			
			if(!content)
			{
				alert("������ �Է��ϼ���.");
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
				// ������� �����´�.
				var resultText = httpRequest.responseText;
				if(resultText == 1){ 
					document.location.reload(); // �󼼺��� â ���ΰ�ħ
				}
			}
		}
		
		// ��� �亯â
		function cmReplyOpen(comment_num){
			var userId = '${sessionScope.sessionID}';
			
			if(userId == "" || userId == null){
				alert("�α����� ��밡���մϴ�.");
				return false;
			}
			else{
				// ��� �亯â open
				window.name = "parentForm";
				window.open("CommentReplyFormAction.co?num="+comment_num,
							"replyForm", "width=570, height=350, resizable = no, scrollbars = no");
			}
		}
		
		// ��� ����â
		function cmDeleteOpen(comment_num){
			var msg = confirm("����� �����մϴ�.");	
			if(msg == true){ // Ȯ���� �������
				deleteCmt(comment_num);
			}
			else{
				return false; // �������
			}
		}
	
		// ��� ����
		function deleteCmt(comment_num)
		{
			var param="comment_num="+comment_num;
			
			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentDeleteAction.co", true);	
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
			httpRequest.send(param);
		}
		
		// ��� ����â
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
			<h2 class="title01">��й�</h2>
			<div class="board_view_area">
				<form method="post" name="frm">
					<h3 class="board_view_title">${board.board_subject}</h3>
					
					<!-- �Խ��� ���� -->
					<div class="board_view_top">
						<span class="info">- Date : ${board.board_date} </span>
						<span class="info">- Writer : ${board.board_id}</span>
						<span class="info">- Hit : ${board.board_count}</span>
					</div>

					<!-- ÷������ -->
					<div class="board_view_file">
						<h3 class="tit">÷������</h3>
						<ul class="clear">
							<li><a href='FileDownloadAction.bo?file_name=${board.board_file}'>${board.board_file}</a></li>
						</ul>
					</div>

					<!-- board_write -->
					<div class="board_view">
						<div class="view_content">${fn:replace(board.board_content, cn, br)}</div>
					</div>

					<!-- board_write bottom -->
					<div class="clearfix board_view_bottom">
						<div class="button_area_left">
							
							<c:if test="${id !=null}">								
								<%-- <c:if test="${id == board.board_id}"> --%>
									<input type="button" value="����" onclick="doAction(0)" class="btn01">
									<input type="button" value="����" onclick="doAction(1)" class="btn01">
									<input type="button" value="���" onclick="changeView(1)" class="btn01">
								<%-- </c:if> --%>
							</c:if>
						</div>						
						<div class="button_area_right">
							<a href="${PATH }/BoardListAction.bo" class="btn01">���</a>
						</div>
						<!-- javascript:location.href='BoardListAction.bo?page=${pageNum}' -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<br> <br> <br> <br> <br> <br> <br> <br>
	<br> <br>
	<!-- ��� �κ� -->
	<div id="comment">
		<table border="1" bordercolor="lightgray">
			<!-- ��� ��� -->
			<c:if test="${requestScope.commentList != null}">
				<c:forEach var="comment" items="${requestScope.commentList}">
					<tr>
						<!-- ���̵�, �ۼ���¥ -->
						<td width="150">
							<div>
								<c:if test="${comment.comment_level > 1}">
							&nbsp;&nbsp;&nbsp;&nbsp; <!-- �亯���ϰ�� ���̵� �տ� ������ �ش�. -->
									<img src="img/reply_icon.gif">
								</c:if>

								${comment.comment_id}<br> <font size="2" color="lightgray">${comment.comment_date}</font>
							</div>
						</td>
						<!-- �������� -->
						<td width="550">
							<div class="text_wrapper">
								${fn:replace(comment.comment_content, cn, br)}</div>
						</td>
						<!-- ��ư -->
						<td width="100">
							<div id="btn">
								<a href="#" onclick="cmReplyOpen(${comment.comment_num})">[�亯]</a><br>
								<!-- ��� �ۼ��ڸ� ����, ���� �����ϵ��� -->
								<c:if test="${comment.comment_id == sessionScope.sessionID}">
									<a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[����]</a>
									<br>
									<a href="#" onclick="cmDeleteOpen(${comment.comment_num})">[����]</a>
								</c:if>
							</div>
						</td>
					</tr>

				</c:forEach>
			</c:if>

			<!-- �α��� ���� ��츸 ��� �ۼ����� -->
			<c:if test="${sessionScope.sessionID !=null}">
				<tr bgcolor="#F5F5F5">
					<form id="writeCommentForm">
						<input type="hidden" name="comment_board"
							value="${board.board_num}"> <input type="hidden"
							name="comment_id" value="${sessionScope.sessionID}">
						<!-- ���̵�-->
						<td width="150">
							<div>${sessionScope.sessionID}</div>
						</td>
						<!-- ���� �ۼ�-->
						<td width="550">
							<div>
								<textarea name="comment_content" rows="4" cols="70"></textarea>
							</div>
						</td>
						<!-- ��� ��� ��ư -->
						<td width="100">
							<div id="btn">
								<p>
									<a href="#" onclick="writeCmt()">[��۵��]</a>
								</p>
							</div>
						</td>
					</form>
				</tr>
			</c:if>
		</table>
	</div>
</section>
</body>
</html>