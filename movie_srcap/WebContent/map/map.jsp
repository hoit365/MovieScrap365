<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<script type="text/javascript" src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=lowRtjfOo6Ap3e0CORe1"></script>
<!-- content -->
<section class="content">
	<div class="content_area joinFin_area clearfix">
		<div class="section">
			<ul class="list"></ul>
			<div id="testMap" style="border:1px solid #000; width:500px; height:400px; margin:20px;"></div>
			<script type="text/javascript">
			    var oPoint = new nhn.api.map.LatLng(37.5010226, 127.0396037);
			    nhn.api.map.setDefaultPoint('tm128');
			    oMap = new nhn.api.map.Map('testMap', {
			        point: oPoint,
			        zoom: 10,
			        enableWheelZoom: true,
			        enableDragPan: true,
			        enableDblClickZoom: false,
			        mapMode: 0,
			        activateTrafficMap: false,
			        activateBicycleMap: false,
			        minMaxLevel: [1, 14],
			        size: new nhn.api.map.Size(500, 400)
			    });
			    
			    var mapZoom = new nhn.api.map.ZoomControl(); // - 줌 컨트롤 선언
			    mapZoom.setPosition({left:20, bottom:20}); // - 줌 컨트롤 위치 지정.
			    oMap.addControl(mapZoom); // - 줌 컨트롤 추가.
			    
			    var oSize = new nhn.api.map.Size(28, 37);
			    var oOffset = new nhn.api.map.Size(14, 37);
			    var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);
			   
			    // - Draggable Marker 의 경우 Icon 인자는 Sprite Icon이 된다.
			    // - 따라서 Sprite Icon 을 사용하기 위해 기본적으로 사용되는 값을 지정한다.
			    // - Sprite Icon 을 사용하기 위해서 상세한 내용은 레퍼런스 페이지의 nhn.api.map.SpriteIcon 객체를 참조하면 된다.
			    
			 	var oMarker1 = new nhn.api.map.Marker(oIcon, { title : '마커옆에 떠 있는 설명 을 이곳에 적어주세요 ' });  //마커 생성 
			 	oMarker1.setPoint(oPoint); //마커 표시할 좌표 선택
				oMap.addOverlay(oMarker1); //마커를 지도위에 표현 
				
				var oLabel1 = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언. 
				oMap.addOverlay(oLabel1); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨. 
				oLabel1.setVisible(true, oMarker1); // 마커 라벨 보이기 
			
				$.ajax({
					"url" : "apimap.jsp",
					"dataType" : "json"
				}).done(function(data){
			
					
					var items = data.items;
					var output = "";
					for( var i = 0; i< items.length;i++){
						var x = (i+1)*0.01;
						var y = (i+1)*0.001;
						console.log(items[i].mapx+"/"+items[i].mapy);
						
						var oMarker2 = new nhn.api.map.Marker(oIcon, { title : '마커옆에 떠 있는 설명 을 이곳에 적어주세요 ' });  //마커 생성 
						oMarker2.setPoint(new nhn.api.map.TM128(items[i].mapx, items[i].mapy).toLatLng()); //마커 표시할 좌표 선택
						oMap.addOverlay(oMarker2); //마커를 지도위에 표현 
						output+="<li>"+items[i].address+"////"+items[i].mapx+"//"+items[i].mapy+"</li>";
					}
					
					$(".list").append(output);
					console.log(data);
				});
				
			   </script>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>