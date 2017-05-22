<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area">
		<div class="section movie_area">
			<h2 class="title01">영화 동영상</h2>
			<div class="youtube_list_area">
				<h2 class="title02">영화이미지</h2>
				<div class="viewer">
					<ul class="clear clearfix" id="youtube_list">
					</ul>
				</div>
				<div class="slider_nav"></div>
			</div>
			<div class="youtube_player" id="player"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
				dataType : "json",
				url : "https://www.googleapis.com/youtube/v3/search",
				data : {
					"part" : "snippet",
					"q" : "캐리비안의 해적 - 보물을 찾아서 예고편",
					"key" : "AIzaSyCrjthm-LB1bS-W5ejJMnBNnTgXFE0DK6M"
				}
			}).done(function(data){
				var youtube_img = "";
				
				for( var i = 0; i<data.items.length;i++){
					youtube_img += "<li><a href=javascript:yt_play('"+data.items[i].id.videoId+"')><img src="+data.items[i].snippet.thumbnails.default.url+" /></a></li>";
				}

				$("#youtube_list").append(youtube_img);
		
			});
		});
		
		function yt_play(youtube_id){
			var player = $("#player");
			var output = '<iframe id="video" width="80%" height="500" src="//www.youtube.com/embed/'+youtube_id+'?rel=0" frameborder="0" allowfullscreen></iframe>';
			player.empty().append(output);
		}
	</script>
</section>
<c:import url="../layout/footer.jsp"></c:import>