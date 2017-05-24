package net.movie.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MovieAPI {
	
	
	private String search = "title";
	private String stx = "";
	
	private String sort = "";
	private int page = 1;
	
	private int listCount = 100;
	private String query = null;
	private String search2 = null;
	private String stx2 = null;
	private final String API_KEY = "DE611070AD8C97B8FAE32AFB839EF9CA3E764F776407F406616C6FF56A2";// 애플리케이션 클라이언트 아이디값";
	
	
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStx() {
		return stx;
	}

	public void setStx(String stx) {
		this.stx = stx;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getSearch2() {
		return search2;
	}

	public void setSearch2(String search2) {
		this.search2 = search2;
	}

	public String getStx2() {
		return stx2;
	}

	public void setStx2(String stx2) {
		this.stx2 = stx2;
	}

	public String getResult(){

		StringBuilder sb = new StringBuilder();
		
		try {

			StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp?");
			urlBuilder.append(URLEncoder.encode("collection","utf-8")+"="+URLEncoder.encode("kmdb_new", "utf-8"));
			urlBuilder.append("&"+URLEncoder.encode("ServiceKey", "utf-8")+"="+URLEncoder.encode(API_KEY, "utf-8"));
			urlBuilder.append("&"+URLEncoder.encode(search, "utf-8")+"="+URLEncoder.encode(stx,"utf-8"));
			urlBuilder.append("&"+URLEncoder.encode("listCount", "utf-8")+"="+URLEncoder.encode(""+this.listCount,"utf-8"));
			
			//페이징
			urlBuilder.append("&"+URLEncoder.encode("startCount", "utf-8")+"="+URLEncoder.encode(""+(this.page-1)*10,"utf-8"));
			if( search2 != null && stx2 != null){
				urlBuilder.append("&"+URLEncoder.encode(search2, "utf-8")+"="+URLEncoder.encode(stx2,"utf-8"));
				urlBuilder.append("&"+URLEncoder.encode("detail=Y", "utf-8"));
			}

			//정렬
			if ( sort != null && !sort.equals("")){
				urlBuilder.append("&"+URLEncoder.encode("sort", "utf-8")+"="+URLEncoder.encode(this.sort,"utf-8"));
			}
			
			System.out.println(urlBuilder.toString());
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/json");

			int responseCode = con.getResponseCode();
			
			BufferedReader rd;
			if (responseCode == 200) { // 정상 호출
				rd = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			} else { // 에러 발생
				rd = new BufferedReader(new InputStreamReader(con.getErrorStream(),"utf-8"));
			}
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line.trim());
			}
			
			rd.close();
			con.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return sb.toString();
	}
		
}
