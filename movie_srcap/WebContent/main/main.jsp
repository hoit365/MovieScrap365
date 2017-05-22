<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
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
	<!-- main_visual_area -->
	<div class="main_visual_area">
		<div class="visual_area">
			<ul class="bxslider">
				<li><img src="${IMG_PATH }/main/visual_img01.jpg" /></li>
				<li><img src="${IMG_PATH }/main/visual_img02.jpg" /></li>
				<li><img src="${IMG_PATH }/main/visual_img03.jpg" /></li>
			</ul>
		</div>
	</div>
	<!-- //main_visual_area -->
	
	<div class="main_area1">
		<!-- movie_list01 -->
		<div class="movie_list01 movie_list_area slider_list">
			<h2 class="title_main">최신개봉작</h2>
			<div class="viewer">
				<ul class="clear clearfix">
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (1).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (2).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (3).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (4).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (10).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (11).jpg" alt="" /></a></li>					
				</ul>
			</div>
			<div class="slider_nav"></div>
		</div>
		<!-- //movie_list01 -->	
		<a href="#" class="btn_more">더보기</a>
	</div>	

	<div class="clearfix main_area2">
		<div class="area_left">
			<!-- movie_list02 -->
			<div class="movie_list02 movie_list_area">
				<h2 class="title_main">개봉예정작</h2>
				<ul class="clear clearfix">
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (10).jpg" alt="" /></a></li>
					<li><a href="#"><img src="${IMG_PATH }/poster/poster (11).jpg" alt="" /></a></li>
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
						매거진매거진
					</div>
					<!-- //매거진 -->
				</div>
				<div class="tab_content" id="tab_moviescrap">
					<!-- 순위 -->
					<div class="movie_lank_area">
						<ul class="clear">
							<li>
								<span class="num">1.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (1).jpg" alt="" /></span>
								<span class="tit ellipsis">센과치히로의 행방불명</span>
								<span class="count">20,555</span>
							</li>
							<li>
								<span class="num">2.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (2).jpg" alt="" /></span>
								<span class="tit ellipsis">바람의 계곡 </span>
								<span class="count">11,555</span>
							</li>
							<li>
								<span class="num">3.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (3).jpg" alt="" /></span>
								<span class="tit ellipsis">토토로</span>
								<span class="count">10,555</span>
							</li>	
							<li>
								<span class="num">3.</span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (5).jpg" alt="" /></span>
								<span class="tit ellipsis">토토로</span>
								<span class="count">9,555</span>
							</li>							
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