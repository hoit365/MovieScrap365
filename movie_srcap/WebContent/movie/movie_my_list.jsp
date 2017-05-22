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
			<!-- 뿌려줄 곳 -->
			나의 스크랩 목록
			<div class="movie_list">
				<ul class="clear">
				<% for(int i=0; i< movieList.size(); i++) {
					MovieBean ml = (MovieBean) movieList.get(i);%>
				<%-- <c:forEach items="${movieList }" var="movie"> --%>
				<li>
					<div class="img">
						<a href="MovieScrapView.mv?seq=<%= ml.getMs_seq() %>&id=<%= ml.getMs_id() %>" >
							<img src='<%= ml.getMs_poster() %>' alt='' />
							
						<%-- <c:if test="${empty movie.poster }">
							<img src='${PATH }/public/images/common/no_img.png' alt='NO IMAGE' />
						</c:if> --%>
						</a>
					</div>
					<div class="info">
						<dl>
							<dt><%= ml.getMs_title() %></dt>
							<dd>감독 : <%=ml.getMs_director() %></dd>
							<dd>등급 : <%=ml.getMs_rating() %></dd>
							<dd>스크랩 날짜 : <%=ml.getMs_regdate() %></dd>
							<dd class="movie_btn">
								<a href="MovieScrapView.mv?seq=<%=ml.getMs_seq() %>&id=<%=ml.getMs_id() %>">자세히보기</a>
								<a href="MovieScrapDelete.mv?seq=<%=ml.getMs_seq() %>&id=<%=ml.getMs_id() %>">스크랩해제</a>
							</dd>
						</dl>
					</div>
				</li>
				<%-- </c:forEach> --%>
				<% } %>
				</ul>
			</div>
	</div>
	<div>
		<%if (nowpage <=1 ){ %>
			<a href="">이전</a>
		<% } else {%>
			<a href="./MovieMyList.mv?page=<%= nowpage-1%>">이전</a>
		<% } %>
		<%for (int a=startpage; a<=endpage; a++) {
			if(a==nowpage){ %>
				<%= a %>
			<% } else { %>
				<a href="./MovieMyList.mv?page=<%=a %>"><%= a %></a>
			<% } %>
		<% } %>
		<%if (nowpage>=maxpage) { %>
			다음
		<% } else { %>
			<a href="./MovieMyList.mv?page=<%=nowpage+1%>">다음</a>
		<% } %>
	</div>
</section>
<!-- //content -->

<c:import url="../layout/footer.jsp"></c:import>