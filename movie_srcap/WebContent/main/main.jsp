<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../layout/header.jsp"></c:import>
<%
	List movierankList = (List) request.getAttribute("movierankList");
%>
<!-- content -->
<section class="content main_content">
	<div class="main_search_area">
		<!-- 검색시 영화정보 페이지로 이동 후 검색 -->
		<form action="MovieScrapList.mv" method="get">
			<label class="tit" for="stx">검색할 영화제목을 입력해주세요.</label>
			<input type="text" name="stx" id="stx" value="" size="40" />
			<input type="submit" value="영화검색" class="btn01" />
		</form>
	</div>

	
	<div class="main_area1">
		<!-- movie_list01 -->
		<div class="movie_list01 movie_list_area slider_list">
			<h2 class="title_main">최신작</h2>
			<div class="viewer">
				<ul class="clear clearfix">
				<c:forEach items="${movieLastestList }" var="mv">
					<c:forTokens items="${mv.poster }" delims="|" var="item" begin="0" end="0">
					<li><a href="MovieScrapView.mv?seq=${mv.movieSeq }&id=${mv.movieId }"><img src="${item}" alt="${mv.title }" /></a></li>
					</c:forTokens>
				</c:forEach>		
				</ul>
			</div>
			<div class="slider_nav"></div>
		</div>
		<!-- //movie_list01 -->	
		<a href="${PATH }/MovieScrapList.mv" class="btn_more">더보기</a>
	</div>	

	<div class="clearfix main_area2">
		<div class="area_left">
			<!-- movie_list02 -->
			<div class="movie_list02 movie_list_area">
				<h2 class="title_main">추천작</h2>
				<ul class="clear clearfix">
					<c:forTokens items="${mv1.poster }" delims="|" var="item" begin="0" end="0">
					<li><a href="MovieScrapView.mv?seq=${mv1.movieSeq }&id=${mv1.movieId }"><img src="${item}" alt="" /></a></li>
					</c:forTokens>
					<c:forTokens items="${mv2.poster }" delims="|" var="item" begin="0" end="0">
					<li><a href="MovieScrapView.mv?seq=${mv2.movieSeq }&id=${mv2.movieId }"><img src="${item}" alt="" /></a></li>
					</c:forTokens>
				</ul>
			</div>
			<!-- //movie_list02 -->
		</div>
		<div class="area_right topic_area">
			<h2 class="title_main">Today Topic</h2>
			<div class="tab_area_wrap tab">
				<ul class="clearfix clear tab_title">
					<li><a href="#tab_lank">BOX OFFICE</a></li>
					<li><a href="#tab_magazine">MAGAZINE</a></li>
					<li><a href="#tab_moviescrap">MOVIE SCRAP</a></li>
				</ul>
				<div class="tab_content" id="tab_lank">
					<!-- 순위 -->
					<div class="movie_lank_area ">
						<ul class="clear">
							<li>
								<span class="num">1.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (1).jpg" alt="" /></span>
								<span class="tit ellipsis">센과치히로의 행방불명</span>
							</li>
							<li>
								<span class="num">2.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (2).jpg" alt="" /></span>
								<span class="tit ellipsis">바람의 계곡 </span>
							</li>
							<li>
								<span class="num">3.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (3).jpg" alt="" /></span>
								<span class="tit ellipsis">토토로</span>
							</li>	
							<li>
								<span class="num">4.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (5).jpg" alt="" /></span>
								<span class="tit ellipsis">히말라야</span>
							</li>	
							<li>
								<span class="num">3.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (3).jpg" alt="" /></span>
								<span class="tit ellipsis">토토로</span>
							</li>	
							<li>
								<span class="num">4.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (5).jpg" alt="" /></span>
								<span class="tit ellipsis">히말라야</span>
							</li>														
						</ul>
					</div>
					<!-- //순위 -->
				</div>
				<div class="tab_content" id="tab_magazine">
					<!-- 매거진 -->
					<div class="magazine_area">
						<h4 class="tit">추천 매거진</h4>
						<img src="${IMG_PATH}/poster/mazine_01.jpg" alt="" />
					</div>
					<!-- //매거진 -->
				</div>
				<div class="tab_content" id="tab_moviescrap">
					
					<!-- 순위 -->
					<div class="movie_lank_area">
						<ul class="clear">
							<c:forEach items="${movieLankList }" varStatus="state" var="mv">
							<li>
								<span class="num">${state.count }.</span>
								<c:choose>
								<c:when test="${empty mv.ms_poster}">
								<span class="img"><span class="no_img">NO</span></span>
								</c:when>
								<c:otherwise>
								<span class="img"><img src="${mv.ms_poster}" alt="" /></span>
								</c:otherwise>
								
								</c:choose>
								
								<span class="tit ellipsis">${mv.ms_title}</span>
								<span class="count">${mv.ms_cnt}</span>
							</li>
							</c:forEach>
						</ul>
					</div>
					<!-- //순위 -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$('.bxslider').bxSlider({
				mode : 'fade',
				auto: true
			});
		});
		tabProduct($(".topic_area"), 0);
	</script>
</section>
<!-- //content -->
<c:import url="../layout/footer.jsp"></c:import>