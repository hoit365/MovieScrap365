<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../layout/header.jsp"></c:import>
<%
	
	List movieList = (List) request.getAttribute("movielist");
	int listcount = ((Integer)request.getAttribute("listcount")).intValue();
	int nowpage = ((Integer)request.getAttribute("page")).intValue();
	int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer)request.getAttribute("startpage")).intValue();
	int endpage = ((Integer)request.getAttribute("endpage")).intValue();
%>
<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section movie_area">
			<h2 class="title01">영화 스크랩 정보</h2>
			<p>스크랩된 영화목록을 볼 수 있습니다.</p>
			
			<!-- 스크랩리스트 -->
			<div class="movie_scrap_list">
				<div class="notice_msg">${msg }</div>
				<ul class="clear">
				<% for(int i=0; i< movieList.size(); i++) {
					MovieBean ml = (MovieBean) movieList.get(i);%>
				<li>
					<div class="img">
						<a href="MovieScrapView.mv?seq=<%= ml.getMs_seq() %>&id=<%= ml.getMs_id() %>" >
						
						<% if( ml.getMs_poster() == null ){ %>
							<img src='${PATH }/public/images/common/no_img.png' alt='NO IMAGE' class="no_img" />
						<% }else{ %>
							<img src='<%= ml.getMs_poster() %>' alt='<%= ml.getMs_title() %> 포스터' />
						<% } %>
						</a>
					</div>
					<div class="info">
						<dl>
							<dt><%= ml.getMs_title() %></dt>
							<dd>감독 : <%=ml.getMs_director() %></dd>
							<% if(ml.getMs_rating() != null ){ %>
							<dd>등급 : <%=ml.getMs_rating() %></dd>
							<% } %>
							<dd>스크랩 날짜 : <%=ml.getMs_regdate() %></dd>
							<dd class="movie_btn">
								<a href="MovieScrapView.mv?seq=<%=ml.getMs_seq() %>&id=<%=ml.getMs_id() %>">자세히보기</a>
								<a href="MovieScrapDelete.mv?seq=<%=ml.getMs_seq() %>&id=<%=ml.getMs_id() %>" class="scrap_del">스크랩해제</a>
							</dd>
						</dl>
					</div>
				</li>
				<%-- </c:forEach> --%>
				<% } %>
				<% if( movieList == null || movieList.size() == 0 ){ %>
				<li class="empty_list"><p>등록된 스크랩이 없습니다.</p></li>
				<% } %>
				</ul>
			</div>
			<!-- //스크랩리스트 -->
		</div>
		
		<div class="page_area">
			<%if (nowpage <=1 ){ %>
				
			<% } else {%>
				<a href="./MovieMyList.mv?page=<%= nowpage-1%>" class="page_prev">이전</a>
			<% } %>
			<%for (int a=startpage; a<=endpage; a++) {
				if(a==nowpage){ %>
					<em><%= a %></em>
				<% } else { %>
					<a href="./MovieMyList.mv?page=<%=a %>"><%= a %></a>
				<% } %>
			<% } %>
			<%if (nowpage>=maxpage) { %>
				
			<% } else { %>
				<a href="./MovieMyList.mv?page=<%=nowpage+1%>"  class="page_next">다음</a>
			<% } %>
		</div>
	</div>
</section>
<!-- //content -->
<script>
	$(document).ready(function(){
		$(".scrap_del").click(function(e){
			var del_url = $(this).attr('href');
			e.preventDefault();
			//삭제
			$.ajax({
				url : del_url
			}).done(function(data){
				alert(data);
				location.reload();
			});
		});
	});
</script>
<c:import url="../layout/footer.jsp"></c:import>