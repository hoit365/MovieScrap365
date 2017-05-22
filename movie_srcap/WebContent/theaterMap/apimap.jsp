<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//java코드로 특정 url에 요청보내고 응답받기
//기본 자바 API를 활용한 방법

 	request.setCharacterEncoding("UTF-8");

	String sido="";
	String theater="";

	if(request.getParameter("sido")!=null){
		sido = request.getParameter("sido");
	}
	
	if(request.getParameter("theater")!=null) {
		theater = request.getParameter("theater");
	}


	String clientID="K7ctvHvLJvBESKp25IuD"; //네이버 개발자 센터에서 발급받은 clientID입력
	String clientSecret = "9ijdH5qayk";        //네이버 개발자 센터에서 발급받은 clientSecret입력

try {
	
	System.out.println("apimap.jsp의 sido : "+sido+"///theater : "+theater);
	
	String search = theater + "&+" +sido;
	
//    String text = URLEncoder.encode("영화관", "UTF-8");
    String text = URLEncoder.encode(search, "UTF-8");
    String display = request.getParameter("display");
    String start = request.getParameter("start");
    
    String apiURL = "https://openapi.naver.com/v1/search/local?query="+text + "&display="+ display +"&start="+start; // json 결과
    //String apiURL = "https://openapi.naver.com/v1/search/local.xml?query="+ text; // xml 결과
    
    System.out.println(apiURL);
    URL url = new URL(apiURL);
    HttpURLConnection con = (HttpURLConnection)url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("X-Naver-Client-Id", clientID);
    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
    int responseCode = con.getResponseCode();
    BufferedReader br;
    if(responseCode==200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    } else {  // 에러 발생
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
    }
    String inputLine;
    StringBuffer rs = new StringBuffer();
    while ((inputLine = br.readLine()) != null) {
        rs.append(inputLine);
    }
    
    br.close();
    out.print(rs.toString());
    
} catch (Exception e) {
    System.out.println(e);
}
%>