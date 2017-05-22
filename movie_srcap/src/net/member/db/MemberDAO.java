package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

import com.oracle.jrockit.jfr.RequestableEvent;

import net.admin.db.AdminBean;

public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public MemberDAO() {
		try{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB Connection fail :"+ex);
			return;
		}
	}
	public boolean MemberInfoUpdate(MemberBean member){
		String sql = "UPDATE MEMBER SET MB_PW = ?, MB_PH = ?, MB_EMAIL = ?  WHERE MB_ID=?";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_PW());
			pstmt.setString(2, member.getMB_PH());
			pstmt.setString(3, member.getMB_EMAIL());
			pstmt.setString(4, member.getMB_ID());
			result = pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		} catch (Exception e) {
			System.out.println("joinMember Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return false;
	}
	
	public boolean MemberPwUpdate(MemberBean member){
		String sql = "UPDATE MEMBER SET MB_PW = ?  WHERE MB_ID=?";
		int result = 0;
		boolean loginOk = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_PW());
			pstmt.setString(2, member.getMB_ID());
			result = pstmt.executeUpdate();
			
			if(result!=0){
				loginOk= true;
			}else{loginOk=false;}
		} catch (Exception e) {
			System.out.println("MemberPwReset Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return loginOk;
	}
	
	public boolean dupChk(String mb_id){
		String sql = "select MB_ID from MEMBER where MB_ID=?";
		boolean result = false;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("MB_ID").equals(mb_id)){
					result = false; //일치
				}
			} else {
				result = true; //아이디가 존재 x
			}
		} catch (Exception e) {
			System.out.println("dupChk Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		
		return result;
		
	}
	public boolean LoginChk(MemberBean member ){
		String sql = "select MB_ID, MB_PW,MB_NAME from MEMBER where MB_ID=?";
		boolean result = false;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			rs = pstmt.executeQuery();
			System.out.println(member.toString());
			if(rs.next()){
				if(rs.getString("MB_ID").equals(member.getMB_ID()) 
						&& rs.getString("MB_PW").equals(member.getMB_PW())){
					member.setMB_NAME(rs.getString("MB_NAME"));
					result = true; //아이디 비밀번호 일치
				}
			} else {
				System.out.println(member.toString());
				result = false; //아이디와 비밀번호가 일치 하지 않음
			}
		} catch (Exception e) {
			System.out.println("LoginChk Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		
		return result;
		
	}
	public int isMember(MemberBean member){
		String sql = "select MB_PW from MEMBER where MB_ID=?";
		int result = -1;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("MB_PW").equals(member.getMB_PW())){
					result = 1; //일치
				} else {
					result = 0;
				}
			} else {
				result = -1; //아이디가 존재 x
			}
		} catch (Exception e) {
			System.out.println("isMember Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		
		return result;
	}
	public String findMyId(MemberBean member){
		String sql = "select MB_NAME, MB_PH, MB_ID from MEMBER where MB_NAME = ? and MB_PH = ? ";
		String result = "";
		System.out.println("DAO 진입");
		try {
			con = ds.getConnection();
			
			System.out.println(member.toString());
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_NAME());
			pstmt.setString(2, member.getMB_PH());
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next() ){
				if(rs.getString("MB_NAME").equals(member.getMB_NAME()) && 
						rs.getString("MB_PH").equals(member.getMB_PH())){
					//member.setMB_ID(rs.getString("MB_ID")); //아이디 반환
					result = rs.getString("MB_ID");//아이디 찾기 완료
				} else {
					result = "";
				}
			} else {
				result = ""; //아이디가 존재 x
			}
		} catch (Exception e) {
			
			System.out.println("findMyId Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}

		return result;
	}
	
	public boolean findMyPw(MemberBean member){
		String sql = "select MB_ID, MB_PH from MEMBER where MB_ID = ? and MB_PH = ? ";
		boolean result = false;
		System.out.println("DAO 진입");
		try {
			con = ds.getConnection();
			
			System.out.println(member.toString());
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			pstmt.setString(2, member.getMB_PH());
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next() ){
				if(rs.getString("MB_ID").equals(member.getMB_ID()) && 
						rs.getString("MB_PH").equals(member.getMB_PH())){

					result = true;//패스워드 수정 허가
				} else {
					System.out.println("회원정보 일치 안함");
					result = false;//회원정보 일치 하지 않음
				}
			} else {
				System.out.println("검색결과 없음");
				result = false;//DB에서 찾을 수 없었음
			}
		} catch (Exception e) {
			
			System.out.println("findMyPw Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}

		return result;
	}
	
	public MemberBean memberUpdate(MemberBean member){
		String sql = "select MB_ID, MB_PW, MB_NAME, MB_BIRTH, MB_GENDER, "
				+ "MB_PH, MB_EMAIL FROM MEMBER WHERE MB_ID = ? and MB_PW = ?";
		System.out.println("DAO 진입");
		String msg="아이디가 잘못 입력 되었어요";
		try {

			System.out.println(member.toString());
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			pstmt.setString(2, member.getMB_PW());
			rs = pstmt.executeQuery();
			
			if(rs.next() ){
				if(rs.getString("MB_ID").equals(member.getMB_ID()) && 
						rs.getString("MB_PW").equals(member.getMB_PW())){
					member.setMB_ID(rs.getString("MB_ID"));
					member.setMB_PW(rs.getString("MB_PW"));
					member.setMB_NAME(rs.getString("MB_NAME"));
					member.setMB_BIRTH(rs.getString("MB_BIRTH"));
					member.setMB_GENDER(rs.getString("MB_GENDER"));
					member.setMB_PH(rs.getString("MB_PH"));
					member.setMB_EMAIL(rs.getString("MB_EMAIL"));
					
					System.out.println("test successful");
				} else {
					System.out.println("아이디 비밀번호 일치하지 않음");
					System.out.println(member.toString());
					return null;//아이디 비밀번호가 일치 하지 않음
				}
			} else {
				System.out.println("정보 조회 이상");
				System.out.println(member.toString());
				return null;//정보 조회 이상
			}
		} catch (Exception e) {
			
			System.out.println("findMyId Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return member;
	}
	
	public int myPageAuth(MemberBean member){
		String sql_1 = "select MB_PW from MEMBER where MB_ID=?";
		int result = -1;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql_1);
			pstmt.setString(1, member.getMB_ID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("MB_PW").equals(member.getMB_PW())){
					result = 1; //일치
				} else {
					result = 0;
				}
			} else {
				result = -1; //아이디가 존재 x
			}
		} catch (Exception e) {
			System.out.println("myPageAuth Error : "+e);
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		
		return result;
	}
	
	public boolean joinMember(MemberBean member) throws SQLException{
		String sql = "insert into MEMBER values(?,?,?,?,?,?,?,?,'no','active')";
		//System.out.println(member.toString());
		int result = 0;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			Calendar date = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String now = dateFormat.format(date.getTime());
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			pstmt.setString(2, member.getMB_PW());
			pstmt.setString(3, member.getMB_NAME());
			pstmt.setString(4, member.getMB_BIRTH());
			pstmt.setString(5, member.getMB_GENDER());
			pstmt.setString(6, member.getMB_PH());
			pstmt.setString(7, member.getMB_EMAIL());
			pstmt.setString(8, now);
			System.out.println(member.toString());
			result = pstmt.executeUpdate();
			con.commit(); 
			if(result!=0){
				return true;
			}
		} catch (Exception e) {
			System.out.println("DB insert error");
			con.rollback(); 
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try { pstmt.close();} catch(SQLException ex){}
			if(con!=null) try { con.close();} catch(SQLException ex){}
		}
		return false;
	}
}
