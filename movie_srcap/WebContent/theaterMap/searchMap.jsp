<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<script type="text/javascript" src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=lowRtjfOo6Ap3e0CORe1"></script>
<script type="text/javascript">

$(function(){
	$("#btnsend").click(function() {
		var input = $("input[name='start']");
		var start = parseInt(input.val());
		start =1;
		input.val(start);
		getAjax();
		
	});
});

function getAjax(){
	var formData = $("#searchform").serialize();
	
	$.ajax({
		type : "post",
		url  : "${PATH}/theaterMap/apimap.jsp",
		dataType : "json",
		data : formData,
		success : onSuccess
	});
}

var len;
var arrMarker;
var total;
var tot;
var s;
var oLabel1;

function onSuccess(data) {
	
	oLabel1.setVisible(false);
	
	$(".list").empty();
	
	for(var j=0; j<len; j++) {
		oMap.removeOverlay(arrMarker[j]);
	}
		
	var items = data.items;
	var output = "";
	
	
	// json object에서 category가 '영화관'인 것만 필터링
	var filteredItems = items.filter(function(item){
		return item.category === "문화,예술>영화관";
	});

	total = data.total;
	var input = $("input[name='start']");
	var start = parseInt(input.val());
	
	tot = Math.ceil(total/6);	
	$("#totpage").text(tot);
	
	s = Math.ceil(start/6);
	$("#nowpage").text(s);

	
	arrMarker = new Array(filteredItems.length);
	
	var oSize2 = new nhn.api.map.Size(28, 37);
    var oOffset2 = new nhn.api.map.Size(14, 37);
    var oIcon2 = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize2, oOffset2);
		
	for( var i = 0; i< filteredItems.length; i++) {
		
		output+="<tr></tr>"+"<tr><td><li class='MapList'>"+filteredItems[i].title+"</li></td><td>"+filteredItems[i].roadAddress+"</td></tr>";

		var items_title = filteredItems[i].title;
		
		arrMarker[i] = new nhn.api.map.Marker(oIcon2, { title : items_title });  //마커 생성
		arrMarker[i].setPoint(new nhn.api.map.TM128(filteredItems[i].mapx, filteredItems[i].mapy).toLatLng()); //마커 표시할 좌표 선택
		oMap.addOverlay(arrMarker[i]); //마커를 지도위에 표현

	}

	len = filteredItems.length;
		
	$(".list").append(output);
	
	var mapInfoTestWindow = new nhn.api.map.InfoWindow(); // - info window 생성
    mapInfoTestWindow.setVisible(false); // - infowindow 표시 여부 지정.
    oMap.addOverlay(mapInfoTestWindow);     // - 지도에 추가.
    
    oMap.setCenter(arrMarker[0].getPoint());
    
     
	$(".MapList").mouseover(function() {
		
		var index = $(".MapList").index(this);
		console.log(index);
		console.log(arrMarker[index].getTitle());
		
//		oLabel.setVisible(true, arrMarker[index]); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
		
 		mapInfoTestWindow.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">' +
                '<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 2px 2px 2px !important">' +
                arrMarker[index].getTitle()
                + '<span></div>');
		mapInfoTestWindow.setPoint(arrMarker[index].getPoint());
        mapInfoTestWindow.setVisible(true);
        mapInfoTestWindow.setPosition({right: 15, top: 30});
        mapInfoTestWindow.autoPosition();
		
	});
    
    $(".MapList").mouseout(function() {
    	mapInfoTestWindow.setVisible(false);
    });

	 
	
	var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
	oMap.addOverlay(oLabel);
	
	mapInfoTestWindow.attach('changeVisible', function (oCustomEvent) {
        if (oCustomEvent.visible) {
            oLabel.setVisible(false);
        }
    });
	
	oMap.attach('mouseenter', function (oCustomEvent) {

        var oTarget = oCustomEvent.target;
        // 마커위에 마우스 올라간거면
        if (oTarget instanceof nhn.api.map.Marker) {
            var oMarker = oTarget;
            oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
        }
    });

    oMap.attach('mouseleave', function (oCustomEvent) {

        var oTarget = oCustomEvent.target;
        // 마커위에서 마우스 나간거면
        if (oTarget instanceof nhn.api.map.Marker) {
            oLabel.setVisible(false);
        }
    });
    
    oMap.attach('click', function (oCustomEvent) {
        var oPoint = oCustomEvent.point;
        var oTarget = oCustomEvent.target;

        // 마커 클릭하면
        if (oTarget instanceof nhn.api.map.Marker) {
            // 겹침 마커 클릭한거면
            if (oCustomEvent.clickCoveredMarker) {
                return;
            }
            return;
        }
        var oMarker = new nhn.api.map.Marker(oIcon, {title: '마커 : ' + oPoint.toString()});
        oMarker.setPoint(oPoint);
        oMap.addOverlay(oMarker);
    });
 
}

</script>


</head>
<body>

<form id="searchform" method="post" action="">
<input type="hidden" name="display" value="6" />
<input type="hidden" name="start" value="1" />


<div id="search_wrap">
<table>
  <tr>
    <td>
      <select name="sido">
        <option value="서울 ">서울</option>
        <option value="경기 ">경기</option>
        <option value="인천 ">인천</option>
        <option value="대전 ">대전</option>
        <option value="충청 ">충청</option>
        <option value="강원 ">강원</option>
        <option value="광주 ">광주</option>
        <option value="전라 ">전라</option>
        <option value="대구 ">대구</option>
        <option value="부산 ">부산</option>
        <option value="울산 ">울산</option>
        <option value="경상 ">경상</option>
        <option value="제주 ">제주</option>
      </select>
    </td>
  
    <td>
       <select name="theater">
   		 <option value="영화관" selected="selected">영화관</option>
    	 <option value="영화관 CGV">CGV</option>
         <option value="영화관 메가박스">메가박스</option>
         <option value="영화관 롯데시네마">롯데시네마</option>
       </select>
    </td>
    
    <td>
      <input type="button" value="검색" id="btnsend" />
    </td>
  </tr>
</table>

</div>

</form>

<hr />
<div id="demo"></div>

<div id="testMap" style="border:1px solid #000; width:500px; height:400px; margin:20px;"></div>

<script type="text/javascript">

	getLocation();
	var x = document.getElementById("demo");
	var oMap;	    

	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {
			x.innerHTML = "Geolocation is not supported by this browser.";
		}
	}

	function showPosition(position) {
		console.log(position);
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;	
		
		var oPoint = new nhn.api.map.LatLng(latitude, longitude);
	    nhn.api.map.setDefaultPoint('LatLng');
	    oMap = new nhn.api.map.Map('testMap', {
		        point: oPoint,
	    	    zoom: 6,
	        	enableWheelZoom: true,
	        	enableDragPan: true,
		        enableDblClickZoom: false,
		        mapMode: 0,
		        activateTrafficMap: false,
		        activateBicycleMap: false,
		        minMaxLevel: [1, 14],
		        size: new nhn.api.map.Size(800, 400)  });
		    
		var mapZoom = new nhn.api.map.ZoomControl(); // - 줌 컨트롤 선언
		mapZoom.setPosition({left:20, bottom:20}); // - 줌 컨트롤 위치 지정.
		oMap.addControl(mapZoom); // - 줌 컨트롤 추가.
		    
		    
		var oSize = new nhn.api.map.Size(42, 60);
		var oOffset = new nhn.api.map.Size(21, 60);
		var oIcon = new nhn.api.map.Icon('/movie_srcap/theaterMap/brown.png', oSize, oOffset);
		   
		// - Draggable Marker 의 경우 Icon 인자는 Sprite Icon이 된다.
		// - 따라서 Sprite Icon 을 사용하기 위해 기본적으로 사용되는 값을 지정한다.
		// - Sprite Icon 을 사용하기 위해서 상세한 내용은 레퍼런스 페이지의 nhn.api.map.SpriteIcon 객체를 참조하면 된다.
		  
		var oMarker1 = new nhn.api.map.Marker(oIcon, { title : '현재위치 ' });  //마커 생성 
		oMarker1.setPoint(oPoint); //마커 표시할 좌표 선택
		oMap.addOverlay(oMarker1); //마커를 지도위에 표현 
		
		oLabel1 = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언. 
		oMap.addOverlay(oLabel1); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨. 
		oLabel1.setVisible(true, oMarker1); // 마커 라벨 보이기 
	}

	function pre(){
		var input = $("input[name='start']");
		var start = parseInt(input.val());
		
		if(start>1) { start-=6; }
		
		input.val(start);
		getAjax();
	}
	
	function next(){
		var input = $("input[name='start']");
		var start = parseInt(input.val());
				
		if(s < tot) { start+=6; }
			
		input.val(start);
		getAjax();
	}
	
</script>

<div id="theater">
<table>
  <ul class="list"></ul>
</table>
</div>
<div>
<a href="javascript:;" onclick="pre()">◀</a>&nbsp;
<span id="nowpage">/</span>
<span id="totpage"></span>
<a href="javascript:;" onclick="next()">▶</a>
</div>


<c:import url="../layout/footer.jsp"></c:import>