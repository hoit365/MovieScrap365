package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

public class AdminDAO2 {
	private static AdminDAO2 instance = new AdminDAO2();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	private AdminDAO2() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB Connection fail :" + ex);
		}
	}

	public static AdminDAO2 getInstance() {
		return instance;
	}

	public ArrayList<AdminBean> getAdminList(String sfl, String stx, int firstRow, int endRow) {
		ArrayList<AdminBean> list = new ArrayList<>();
		
		//페이징 처리
		String sql = " SELECT * FROM ";
			   sql += "    ( SELECT A.* , ROWNUM AS RNUM FROM ";
			   sql += "         ( SELECT * FROM MEMBER ";
			   //검색이 있을 경우 
			   if( sfl != null && !sfl.equals("") ){
			   sql += "            WHERE "+ sfl + " like ? ";
			   }
			   sql += "   ) A WHERE ROWNUM < ? ) ";
			   sql += " WHERE RNUM >= ? ";

		try {
			int k = 1;
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			if( sfl != null && !sfl.equals("") ){
			pstmt.setString(k++, "%"+stx+"%");
			}
			pstmt.setInt(k++, endRow);
			pstmt.setInt(k++, firstRow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				AdminBean adminBean = new AdminBean();
				
				adminBean.setMB_ID(rs.getString("MB_ID"));
				adminBean.setMB_NAME(rs.getString("MB_NAME"));
				adminBean.setMB_BIRTH(rs.getString("MB_BIRTH"));
				adminBean.setMB_GENDER(rs.getString("MB_GENDER"));
				adminBean.setMB_PH(rs.getString("MB_PH"));
				adminBean.setMB_EMAIL(rs.getString("MB_EMAIL"));
				adminBean.setMB_REGDATE(rs.getString("MB_REGDATE"));
				adminBean.setMB_STAT(rs.getString("MB_STAT"));
				
				list.add(adminBean);
		
			}
		} catch ( Exception e) {
			System.out.println("getAdminList Error : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return list;
	}
	
	//getBoardTotalCount
	public int getBoardTotalCount(String sfl, String stx) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "select count(*) as cnt from member ";
		if( sfl != null && !sfl.equals("")){
			   sql += " where "+sfl+" like ? ";
		}
		System.out.println("sfl"+sfl+"sql:"+sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			if( sfl != null && !sfl.equals("")){
				pstmt.setString(1, "%"+stx+"%");
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");
			}
	
		}catch(Exception e){
			System.out.println("getBoardTotalCount 에러 : "+e.getMessage());
			
		}finally{
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
			}
		}
		return result;
	}
	

	public AdminBean adminMemberView(String mb_id) {
		String sql = "select * from member where mb_id = ?";
		AdminBean bean = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new AdminBean();
	
				bean.setMB_ID(rs.getString("MB_ID"));
				bean.setMB_NAME(rs.getString("MB_NAME"));
				bean.setMB_BIRTH(rs.getString("MB_BIRTH"));
				bean.setMB_GENDER(rs.getString("MB_GENDER"));
				bean.setMB_PH(rs.getString("MB_PH"));
				bean.setMB_EMAIL(rs.getString("MB_EMAIL"));
				bean.setMB_REGDATE(rs.getString("MB_REGDATE"));
				bean.setMB_STAT(rs.getString("MB_STAT"));
			}
			return bean;
		} catch (Exception e) {
			System.out.println("adminMemberView Error : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return bean;
	}


}
