package net.movie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jdk.nashorn.api.scripting.JSObject;
import net.movie.action.Movie;

public class MovieDAO {
	static private MovieDAO instance = null;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private MovieDAO() {}

	
	static public MovieDAO getInstance(){
		if( instance == null ){
			instance = new MovieDAO();
		}
		return instance;
	}
	
	public void connection(){
		try{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB Connection fail :"+ex);
			return;
		}
	}
	
	//스크랩
	public boolean MSScrap(MovieBean movie){
		String sql = "";
		int result = 0;
		int num = 0;
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(ms_no) from MovieScrap");
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
			
			sql = "insert into MovieScrap (ms_no, mb_id, ms_title, "
					+ " ms_director, ms_poster, ms_regdate,"
					+ " ms_rating, ms_seq, ms_id) "
					+ "values(?,?,?,?,?,sysdate,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, movie.getMb_id());
			pstmt.setString(3, movie.getMs_title());
			pstmt.setString(4, movie.getMs_director());
			pstmt.setString(5, movie.getMs_poster());
			pstmt.setString(6, movie.getMs_rating());
			pstmt.setString(7, movie.getMs_seq());
			pstmt.setString(8, movie.getMs_id());
			
			result = pstmt.executeUpdate();
			if(result != 0){
				return true;
			}
		} catch (Exception e) {
			System.out.println("MSScrap ERROR : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return false;
	}
	
	//스크랩 삭제
	public boolean MSScrapDelete(MovieBean movie){
		String sql = "";
		int result = 0;
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement("delete from MovieScrap where mb_id=? and ms_seq=? and ms_id=?");
			pstmt.setString(1, movie.getMb_id());
			pstmt.setString(2, movie.getMs_seq());
			pstmt.setString(3, movie.getMs_id());
			
			result = pstmt.executeUpdate();
			if(result != 0){
				return true;
			}
		} catch (Exception e) {
			System.out.println("MSScrapDelete ERROR : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return false;
	}
	
	
	//스크랩 체크
	public boolean MSScrapCheck(String mb_id, String ms_seq, String ms_id){
		String sql = "select * from MovieScrap where mb_id=? and ms_seq=? and ms_id=?";
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setString(2, ms_seq);
			pstmt.setString(3, ms_id);
			rs = pstmt.executeQuery();
			if (rs.next()){
				System.out.println("중복된 영화입니다");
				return false;
			}
		} catch (Exception e) {
			System.out.println("스크랩 중복 체크 에러 : "+e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
			}
		}
		return true;
	}
	
	
	//스크랩 삭제 체크 ( 스크랩안되있는데 삭제버튼을 누른경우)
	public boolean MSScrapDeleteCheck(String mb_id, String ms_seq, String ms_id){
		String sql = "select * from MovieScrap where mb_id=? and ms_seq=? and ms_id=?";
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setString(2, ms_seq);
			pstmt.setString(3, ms_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
			} else {
				System.out.println("스크랩 안된 영화입니다");
				return true;
			}
		} catch (Exception e) {
			System.out.println("스크랩 안된 영화 삭제 에러 : "+e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
			}
		}
		return false;
	}
	
	
	//리뷰 작성
	public boolean MSReview(MovieBean movie){
		int num = 0;
		String sql="";
		int result = 0;
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(ms_no) from MovieReview");
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
			
			sql = "insert into MovieReview (ms_no, ms_title, mb_id, "
					+ " ms_myrating, ms_review, ms_seq, ms_id, ms_regdate) "
					+ "values(?,?,?,?,?,?,?,sysdate)";
			
			System.out.println("moviedao - title: " +movie.getMs_title());
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, movie.getMs_title());
			pstmt.setString(3, movie.getMb_id());
			pstmt.setInt(4, movie.getMs_myRating());
			pstmt.setString(5, movie.getMs_review());
			pstmt.setString(6, movie.getMs_seq());
			pstmt.setString(7, movie.getMs_id());
			
			result = pstmt.executeUpdate();
			if(result == 0) return false;
			
			return true;
		} catch (Exception e) {
			System.out.println("Review Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
		return false;
	}
	
	
	//리뷰 삭제
	public boolean MSReviewdelete(MovieBean movie){
		int num = 0;
		String sql="";
		int result = 0;
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(ms_no) from MovieReview");
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
			
			sql = "delete from MovieReview where mb_id=? and ms_seq=? and ms_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movie.getMb_id());
			pstmt.setString(2, movie.getMs_seq());
			pstmt.setString(3, movie.getMs_id());
			
			result = pstmt.executeUpdate();
			if(result == 0) return false;
			
			return true;
		} catch (Exception e) {
			System.out.println("Review Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
		return false;
	}
	
	public int getListcount(String seq, String id) {
		int x = 0;

		String sql = "select count(*) from MovieReview where ms_seq = ? and ms_id = ?";

		try {
			connection();
			con = ds.getConnection();

			// for debug
			System.out.println("MovieDAO - getListCount(): " + sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("MovieDAO - getListCount()에러 " + e);
			System.out.println("MovieDAO - getListCount()에러 " + sql);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return x;
	}
	
	/* 3. 리뷰 목록 보기 */
	public List<MovieBean> getReviewList(int page, int limit) {
		String review_list_sql = "select * from "
				+ "(select rownum rnum, ms_no, ms_title, mb_id, ms_myRating, ms_review, "
				+ "ms_seq, ms_id, ms_regdate "
				+ "from (select * from MovieReview order by ms_no desc))"
				+ "where rnum >= ? and rnum <= ? ";
		
		List<MovieBean> list = new ArrayList<MovieBean>();
		
		int startrow = (page-1)*limit + 1;    // 읽기 시작할 row 번호, 한 페이지의 시작 글번호
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호, 한 페이지의 마지막 글 번호
//		int startrow = (page-1)*10 + 1;    // 읽기 시작할 row 번호
//		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호
		
		try {
			connection();
			con = ds.getConnection();
			
			// for debug
			System.out.println("MovieDAO - getList() : " + review_list_sql);
			
			pstmt = con.prepareStatement(review_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieBean reviewBoard = new MovieBean();
				
				reviewBoard.setMs_no(rs.getInt("MS_NO"));
				reviewBoard.setMs_title(rs.getString("MS_TITLE"));
				reviewBoard.setMb_id(rs.getString("MB_ID"));
				reviewBoard.setMs_myRating(rs.getInt("MS_MYRATING"));
				reviewBoard.setMs_review(rs.getString("MS_REVIEW"));
				reviewBoard.setMs_seq(rs.getString("MS_SEQ"));
				reviewBoard.setMs_id(rs.getString("MS_ID"));
				reviewBoard.setMs_regdate(rs.getDate("MS_REGDATE"));
				
				list.add(reviewBoard);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("MovieDAO - getReviewList()에러 " + e);
			System.out.println("MovieDAO - getReviewList()에러 " + review_list_sql);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return null;
	}
	
	
	//글쓴이 인지 확인
		public boolean isReviewWriter(String mb_id, String ms_seq, String ms_id){
			System.out.println("mb_id="+mb_id);
			System.out.println("ms_seq="+ms_seq);
			System.out.println("ms_id="+ms_id);
			String sql = "select count(*) from MovieReview where "
					+ " mb_id=? and ms_seq=? and ms_id=?";
			
			try {
				connection();
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb_id);
				pstmt.setString(2, ms_seq);
				pstmt.setString(3, ms_id);
				rs = pstmt.executeQuery();
				rs.next();
				
				int isWriter = rs.getInt(1);
				
				if(isWriter > 0){
					return true;
				}
				
			} catch (Exception e) {
				System.out.println("isScrapWriter Error : "+e);
				System.out.println(e.toString());
			} finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				} catch (Exception e2) {
				}
			}
			return false;
		}
	
	//스크랩 갯수 확인(로그인한 사람아이디값으로 체크)
	public int getScrapCount(String mb_id){
		int x = 0;
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from MovieScrap where mb_id=?");
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()) x = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("getScrapList Error : "+e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
			}
		}
		return x;
	}
	
	
	//스크랩한 리스트 보기
	public List getScrapList(int page, int limit, String mb_id){
		String sql = "";
		sql = "select * from (select rownum rnum, ms_no, mb_id, ms_title, ms_director, "
				+ "ms_poster, ms_regdate, ms_rating, ms_seq, ms_id from (select * from "
				+ "moviescrap where mb_id=?)) where rnum>=? and rnum<=?";
		
		List list = new ArrayList();
		int startrow = (page -1)*10+1;
		int endrow = startrow+limit-1;
		
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MovieBean movie = new MovieBean();
				movie.setMs_no(rs.getInt("ms_no"));
				movie.setMb_id(rs.getString("mb_id"));
				movie.setMs_title(rs.getString("ms_title"));
				movie.setMs_director(rs.getString("ms_director"));
				movie.setMs_poster(rs.getString("ms_poster"));
				movie.setMs_rating(rs.getString("ms_rating"));
				movie.setMs_seq(rs.getString("ms_seq"));
				movie.setMs_id(rs.getString("ms_id"));
				movie.setMs_regdate(rs.getDate("ms_regdate"));
				
				list.add(movie);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getScrapList Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
		return null;
	}
	
	
	//스크랩시 랭킹 처리를 위해 이미 스크랩 되어있는지 확인
	public boolean MovieRankCheck(String ms_seq, String ms_id){
		String sql = "select * from MovieRank where ms_seq=? and ms_id=?";
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ms_seq);
			pstmt.setString(2, ms_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
			} else {
				System.out.println("아직 스크랩 안된 영화");
				return true;
			}
		} catch (Exception e) {
			System.out.println("MovieRankCheck Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
		return false;
	}
	
	
	//스크랩 체크후 카운트를 위해 MainRank 테이블에 추가해줄 곳
	//MovieRankCheck 에서 값이 없을시 insert할곳
	public boolean MovieRankAdd(MovieBean movie){
		String sql = "";
		
		int result = 0;

		try {
			connection();
			con = ds.getConnection();

			
			sql = "insert into MovieRank (ms_title, ms_poster, ms_seq, ms_id, ms_cnt)"
					+ " values (?,?,?,?,?)";
			pstmt = con.prepareStatement("sql");

			
			pstmt.setString(1, movie.getMs_title());
			pstmt.setString(2, movie.getMs_poster());
			pstmt.setString(3, movie.getMs_seq());
			pstmt.setString(4, movie.getMs_id());
			pstmt.setInt(5, 1);
			result = pstmt.executeUpdate();
			if(result != 0){
				return true;
			}
		} catch (Exception e) {
			System.out.println("MovieRankAdd Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
		return false;
	}
	
	
	//스크랩 횟수 업데이트 ! 더하기 !
	public void setMovieRankCntUp(String seq, String id){
		String sql = "update MovieRank set ms_cnt=ms_cnt+1 where ms_seq=? and ms_id=?";
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("setMovieRankCntUp Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
	}
	
	
	//스크랩 횟수 업데이트 ! 빼기 !
	public void setMovieRankCntDown(String seq, String id){
		String sql = "update MovieRank set ms_cnt=ms_cnt-1 where ms_seq=? and ms_id=?";
		try {
			connection();
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("setMovieRankCntDown Error : "+e);
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(con!=null) try{con.close();} catch(SQLException e){}
		}
	}
	
	
	
	//영화 API 파싱 리스트 
	public ArrayList<Movie> getMovieList(String data){
		ArrayList<Movie> list = new ArrayList<>();
		Movie movie = null;
		try {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject  = (JSONObject) jsonParser.parse(data);
		JSONArray dataArray = (JSONArray) jsonObject.get("Data");
		
		JSONObject dataObject = (JSONObject) dataArray.get(0);
		if( dataObject != null ){
			JSONArray resultArray = (JSONArray) dataObject.get("Result");
			
			if( resultArray != null ){
				for( int i = 0; i< resultArray.size(); i++ ){
					JSONObject resultObject = (JSONObject) resultArray.get(i);
					movie = new Movie();
					movie.setTitle(resultObject.get("title").toString().replaceAll(" !HE ", "").replaceAll(" !HS ", "")); //제목
					movie.setTitleOrg(resultObject.get("titleOrg").toString()); //titleOrg
					
					//감독
					JSONArray directorArray = (JSONArray) resultObject.get("director");
					JSONObject director = (JSONObject) directorArray.get(0);
					movie.setDirector(director.get("directorNm").toString());
					
					//배우
					JSONArray actorArray = (JSONArray) resultObject.get("actor");
					ArrayList<String> actorlist = new ArrayList<>();
					for( int j = 0; j < actorArray.size(); j++ ){
						JSONObject actor = (JSONObject) actorArray.get(j);
						actorlist.add(actor.get("actorNm").toString());
					}
					movie.setActor(actorlist);
					
					//포스터
					if(resultObject.get("posters").toString().equals("")){
						movie.setPoster("");
					} else {
						movie.setPoster(resultObject.get("posters").toString());
					}
					//스틸
					movie.setStlls(resultObject.get("stlls").toString());
					
					//docID
					movie.setDocId(resultObject.get("DOCID").toString());
					
					//movieSeq
					movie.setMovieSeq(resultObject.get("movieSeq").toString());
					
					//movieId
					movie.setMovieId(resultObject.get("movieId").toString());
					
					//prodYear
					movie.setProdYear(resultObject.get("prodYear").toString());
					
					//plot 
					movie.setPlot(resultObject.get("plot").toString());
					
					//company
					movie.setCompany(resultObject.get("company").toString());
					
					//rating
					JSONArray ratingArray = (JSONArray) resultObject.get("rating");
					JSONObject rating = (JSONObject) ratingArray.get(0);
					movie.setRating(rating.get("ratingGrade").toString());
					
					//runtime
					movie.setRuntime(resultObject.get("runtime").toString());
					
					//nation
					movie.setNation(resultObject.get("nation").toString());
					
					//genre
					movie.setGenre(resultObject.get("genre").toString());
					list.add(movie);
				}
			}
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
