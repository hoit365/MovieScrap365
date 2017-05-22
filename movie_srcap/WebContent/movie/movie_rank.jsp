<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<section class="content">
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
								<span class="num"></span>
								<span class="img"><img src="${IMG_PATH }/poster/poster (1).jpg" alt="" /></span>
								<span class="tit ellipsis"></span>
								<span class="count"></span>
							</li>
							
						</ul>
					</div>
					<!-- //순위 -->
				</div>				
			</div>
		</div>
</section>
<c:import url="../layout/footer.jsp"></c:import>