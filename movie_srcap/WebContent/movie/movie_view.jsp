<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<script type="text/javascript">
	function tabreviewClick() {
		var top = $('.movie_content').offset().top;
		$('html, body').scrollTop(top);
		tabProduct($(".movie_content"), 2);
	}
</script>
<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section movie_area">
			<h2 class="title01">${movie.title }</h2>
			<c:choose>
				<c:when test="${movie.titleOrg == '' }">
					<p class="text01">${movie.title },${movie.prodYear }</p>
				</c:when>
				<c:when test="${movie.titleOrg != null }">
					<p class="text01">${movie.titleOrg },${movie.prodYear }</p>
				</c:when>
			</c:choose>
			<div class="movie_detail_top clearfix">
				<div class="movie_img">
					<c:forTokens items="${movie.poster}" delims="|" var="poster"
						begin="0" end="0">
						<img src="${poster}" alt="" />
					</c:forTokens>

				</div>
				<ul class="clear list01 movie_detail_area">
					<li>${api }</li>
					<li><span class="tit">개요</span> <span class="con">${movie.genre }</span>
					</li>
					<li><span class="tit">감독</span> <span class="con">${movie.director }</span>
					</li>
					<li><span class="tit">출연</span> <span class="con"> <c:forEach
								items="${movie.actor }" var="actor" varStatus="status">
								<c:if test="${status.index != 0}">,</c:if>
						${actor }
						</c:forEach>
					</span></li>
					<li><span class="tit">등급</span> <span class="con">${movie.rating }</span>
					</li>
					<li><span class="tit">상영시간</span> <span class="con">${movie.runtime }
							분</span></li>
				</ul>
			</div>
			<div class="btn_area_center movie_btn">
				<a href="./MovieScrapAdd.mv?id=${param.id }&seq=${param.seq}">
					스크랩 </a> <a
					href="./MovieScrapDelete.mv?id=${param.id }&seq=${param.seq}">
					스크랩 삭제 </a> <a href="javascript:tabreviewClick()" id="id_review">리뷰하기</a>
				<a href="">토론하기</a>
			</div>

			<div>
				<h2 class="title01">영화 동영상</h2>
				<div class="youtube_list_area">
					<h2 class="title02">영화 동영상</h2>
					<div class="viewer">
						<ul class="clear clearfix" id="youtube_list">
						</ul>
					</div>
				</div>
				<div class="youtube_player" id="player"></div>
			</div>

			<div class="movie_img_list slider_list">
				<h2 class="title02">영화이미지</h2>
				<div class="viewer">
					<ul class="clear clearfix">

						<c:forTokens items="${movie.stlls}" delims="|" var="stlls">
							<li><a href="#"><img src="${stlls}" alt="" /></a></li>
						</c:forTokens>
						<%-- <li><a href="#"><img src="${IMG_PATH }/poster/mv_img01.jpg" alt="" /></a></li> --%>
					</ul>
				</div>
				<div class="slider_nav"></div>
			</div>
			<!-- movie_content -->
			<div class="movie_content">
				<div class="tab">
					<div class="tab_area_wrap tab">
						<ul class="clearfix clear tab_title">
							<li><a href="#tab_main">주요정보</a></li>
							<li><a href="#tab_actor">배우제작진</a></li>
							<li><a href="#tab_review">리뷰</a></li>
						</ul>

						<div class="tab_content" id="tab_main">${movie.plot }</div>
						<div class="tab_content" id="tab_actor">${movie.actor }</div>
						<form action="./MovieReview.mv?id=${param.id }&seq=${param.seq}"
							method="post" id="frm">
							<div class="tab_content" id="tab_review">



								<div class="btn_area_center movie_btn" id="register">
									<a
										href="./MovieReviewExistence.mv?id=${param.id }&seq=${param.seq}&click=true">평점
										등록 </a>
								</div>

								<c:if test="${click == true }">
									<script>
										$("#register").hide();
									</script>
									<c:choose>
										<c:when test="${isWriter == true }">
											<c:out value="이미 한줄평을 등록하셨습니다."></c:out>
											<table>

												<c:forEach items="${reviewlist }" var="review">
													<c:if test="${review.mb_id == sessionScope.mb_id && param.seq == review.ms_seq}">
														<thead>
															<tr>
																<td colspan="2">${review.ms_regdate }</td>
																<td><a
																	href="./MovieReviewDelete.mv?id=${param.id }&seq=${param.seq}&mb_id=${sessionScope.mb_id}">ⓧ
																		× x X</a></td>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>${review.ms_myRating }</td>
																<td colspan="2">${review.ms_review }</td>
															</tr>
														</tbody>
													</c:if>
												</c:forEach>

											</table>


										</c:when>
										<c:otherwise>
											<input type="hidden" name="ms_title" value="${movie.title }" />
											<textarea name="ms_review" id="ms_review" cols="50" rows="17"></textarea>
											<br>
											<div>
												<label for="ms_myRating">평점&nbsp;&nbsp;</label> <select
													id="ms_myRating" name="ms_myRating">
													<option value="0">0</option>
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5" selected="selected">5</option>
												</select>
											</div>
											<br>
											<ul class="clearfix clear tab_title">
												<li><a href="#"
													onclick="document.getElementById('frm').submit();">저장</a></li>
												<li><a href="#"
													onclick="document.getElementById('frm').reset()">다시작성</a></li>
											</ul>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</form>
					</div>
				</div>
			</div>


			<div class="review_list">
				<table>
					<c:forEach items="${reviewlist }" var="review">
						<c:if test="${review.ms_title eq movie.title }">
							<thead>
								<tr>
									<td>${review.ms_myRating }</td>
									<td>${review.mb_id }</td>
									<td>${review.ms_regdate }
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="3">${review.ms_review }</td>
								</tr>
							</tbody>
						</c:if>
					</c:forEach>

				</table>
			</div>


			<div id="pageForm" class="page_area">
				<c:choose>
					<c:when test="${spage > 1 }">
						<a
							href='MovieScrapView.mv?id=${param.id }&seq=${param.seq}&page=${spage-1}'
							class="page_prev">이전</a>
					</c:when>
					<c:otherwise>
						<a href='#' class="page_prev">이전</a>
					</c:otherwise>
				</c:choose>

				<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${pageNum == spage}">
							<em>${pageNum }</em>
						</c:when>
						<c:otherwise>
							<a
								href='MovieScrapView.mv?id=${param.id }&seq=${param.seq}&page=${pageNum}'>${pageNum}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${spage < maxPage }">
						<a
							href='MovieScrapView.mv?id=${param.id }&seq=${param.seq}&page=${spage+1}'
							class="page_next">다음</a>
					</c:when>
					<c:otherwise>
						<a href='#' class="page_next">다음</a>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.ajax(
											{
												dataType : "json",
												url : "https://www.googleapis.com/youtube/v3/search",
												data : {
													"part" : "snippet",
													"q" : "${movie.title } 예고편",
													"key" : "AIzaSyCrjthm-LB1bS-W5ejJMnBNnTgXFE0DK6M"
												}
											})
									.done(
											function(data) {
												var youtube_img = "";

												for (var i = 0; i < data.items.length; i++) {
													youtube_img += "<li><a href=javascript:yt_play('"
															+ data.items[i].id.videoId
															+ "')><img src="+data.items[i].snippet.thumbnails.default.url+" /></a></li>";
												}

												$("#youtube_list").append(
														youtube_img);

											});
						});

		function yt_play(youtube_id) {
			var player = $("#player");
			var output = '<iframe id="video" width="80%" height="500" src="//www.youtube.com/embed/'
					+ youtube_id
					+ '?rel=0" frameborder="0" allowfullscreen></iframe>';
			player.empty().append(output);
		}
	</script>
</section>
<!-- //content -->

<c:import url="../layout/footer.jsp"></c:import>