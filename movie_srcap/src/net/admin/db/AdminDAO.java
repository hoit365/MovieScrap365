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

public class AdminDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public AdminDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB Connection fail :" + ex);
			return;
		}
	}

	public boolean AdminMemberInfoUpdate(AdminBean member) {
		String sql = "UPDATE MEMBER SET MB_ID = ?, MB_NAME = ?, MB_BIRTH = ?, MB_GENDER = ?, MB_PH = ?, "
				+ "MB_EMAIL = ?, MB_STAT = ?  WHERE MB_ID=?";
		System.out.println(member.toString());
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMB_ID());
			pstmt.setString(2, member.getMB_NAME());
			pstmt.setString(3, member.getMB_BIRTH());
			pstmt.setString(4, member.getMB_GENDER());
			pstmt.setString(5, member.getMB_PH());
			pstmt.setString(6, member.getMB_EMAIL());
			pstmt.setString(7, member.getMB_STAT());
			pstmt.setString(8, member.getMB_ID());
			result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("joinMember Error : " + e);
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
		return false;
	}

	public int LoginChk(AdminBean adminBean) {
		String sql = "select MB_ID,MB_PW, MB_NAME, MB_MANAGER, MB_STAT from MEMBER where MB_ID=?";
		int result = 5;
		// 0 : 로그인 성공
		// 1 : 아이디 비밀번호 일치하지 않음
		// 2 : 어드민 계정이 아님
		// 3 : 비활성화된 어드민 계정
		// 4 : 알수 없는 오류로 로그인 실패
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminBean.getMB_ID());
			rs = pstmt.executeQuery();
			System.out.println(adminBean.toString());

			if (rs.next()) {
				if (rs.getString("MB_ID").equals(adminBean.getMB_ID())
						&& rs.getString("MB_PW").equals(adminBean.getMB_PW())
						&& rs.getString("MB_MANAGER").equals("yes") && rs.getString("MB_STAT").equals("active")) {
					adminBean.setMB_NAME(rs.getString("MB_NAME"));
					result = 0; // 아이디 비밀번호 일치
					System.out.println(result);
				}
				if (rs.getString("MB_PW").equals(adminBean.getMB_PW()) == false) {
					System.out.println(rs.getString("MB_PW"));
					System.out.println(adminBean.getMB_PW());
					System.out.println(adminBean.toString());
					result = 1; // 아이디와 비밀번호가 일치 하지 않음
					System.out.println(result);
				}
				if (rs.getString("MB_MANAGER").equals("no")) {
					System.out.println(adminBean.toString());
					result = 2; // 어드민 계정이 아님
					System.out.println(result);
				}
				if (rs.getString("MB_STAT").equals("inactive")) {
					System.out.println(adminBean.toString());
					result = 3; // 비활성화된 계정
					System.out.println(result);
				}

			}

		} catch (Exception e) {
			System.out.println("LoginChk Error : " + e);
			result = 4;// 알수 없는 오류로 로그인 실패
			System.out.println(result);
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

		return result;

	}

	public AdminBean totalSearchList(AdminBean member){
		String sql = "select count(*) as count FROM MEMBER";
		if (member.getMB_ID() != null) {
			sql += " WHERE MB_ID = '" + member.getMB_ID()+"'";
		} else if (member.getMB_NAME() != null) {
			sql += " WHERE MB_NAME = '" + member.getMB_NAME()+"'";
		} else if (member.getMB_PH() != null) {
			sql += " WHERE MB_PH = '" + member.getMB_PH()+"'";
		} else if (member.getMB_EMAIL() != null) {
			sql += " WHERE MB_EMAIL = '" + member.getMB_EMAIL()+"'";
		} else if (member.getMB_STAT() != null) {
			sql += " WHERE MB_STAT = '" + member.getMB_STAT()+"'";
		}
try {
	System.out.println("total count DAO 진입");
	System.out.println("---------------------------------");
	System.out.println(member.getMB_ID());
	System.out.println(member.getMB_NAME());
	System.out.println(member.getMB_PH());
	System.out.println(member.getMB_EMAIL());
	System.out.println(member.getMB_STAT());
	System.out.println(member.toString());
	System.out.println("---------------------------------");
			System.out.println(sql.toString());
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(member.getPage()==0){
					int i = 1;
					member.setPage(i);
				}
				member.setTotalCount(Integer.parseInt(rs.getString("count")));
				System.out.println("count : "+rs.getString("count"));
				
			}
			

}catch(

	Exception e)
	{

		System.out.println("findMyId Error : " + e);
	}finally
	{
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
return member;
}

	public List adminMemberView(AdminBean member) {
		String sql = "select MB_ID, MB_NAME, MB_BIRTH, MB_GENDER, MB_PH, MB_EMAIL, "
				+ "MB_REGDATE, MB_STAT from member";
		List list = new ArrayList();
		int num = 0;
		
		System.out.println("DAO 진입");
		System.out.println("---------------------------------");
		System.out.println(member.getMB_ID());
		System.out.println(member.getMB_NAME());
		System.out.println(member.getMB_PH());
		System.out.println(member.getMB_EMAIL());
		System.out.println(member.getMB_STAT());
		System.out.println(member.toString());
		System.out.println("---------------------------------");
		if (member.getMB_ID() != null) {
			sql += " where MB_ID = '" + member.getMB_ID() + "'";
		} else if (member.getMB_NAME() != null) {
			sql += " where MB_NAME = '" + member.getMB_NAME() + "'";
		} else if (member.getMB_PH() != null) {
			sql += " where MB_PH = '" + member.getMB_PH() + "'";
		} else if (member.getMB_EMAIL() != null) {
			sql += " where MB_EMAIL = '" + member.getMB_EMAIL() + "'";
		} else if (member.getMB_STAT() != null) {
			sql += " where MB_STAT = '" + member.getMB_STAT() + "'";
		}
		try {

			System.out.println(sql.toString());
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				num++;
				AdminBean adminBean = new AdminBean();		
				adminBean.setNUM(Integer.toString(num));
				adminBean.setMB_ID(rs.getString("MB_ID"));
				adminBean.setMB_NAME(rs.getString("MB_NAME"));
				adminBean.setMB_BIRTH(rs.getString("MB_BIRTH"));
				adminBean.setMB_GENDER(rs.getString("MB_GENDER"));
				adminBean.setMB_PH(rs.getString("MB_PH"));
				adminBean.setMB_EMAIL(rs.getString("MB_EMAIL"));
				adminBean.setMB_REGDATE(rs.getString("MB_REGDATE"));
				adminBean.setMB_STAT(rs.getString("MB_STAT"));
				list.add(adminBean);

				System.out.println(list.size());
			}

			return list;
		} catch (Exception e) {

			System.out.println("findMyId Error : " + e);
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

	public List adminMemberView() {
		String sql = "select MB_ID, MB_NAME, MB_BIRTH, MB_GENDER, MB_PH, MB_EMAIL, "
				+ "MB_REGDATE, MB_STAT from member";
		System.out.println("DAO 진입");
		List list = new ArrayList();
		AdminBean bean = new AdminBean();
		int num = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				num++;
				AdminBean adminBean = new AdminBean();		
				adminBean.setNUM(Integer.toString(num));
				adminBean.setMB_ID(rs.getString("MB_ID"));
				adminBean.setMB_NAME(rs.getString("MB_NAME"));
				adminBean.setMB_BIRTH(rs.getString("MB_BIRTH"));
				adminBean.setMB_GENDER(rs.getString("MB_GENDER"));
				adminBean.setMB_PH(rs.getString("MB_PH"));
				adminBean.setMB_EMAIL(rs.getString("MB_EMAIL"));
				adminBean.setMB_REGDATE(rs.getString("MB_REGDATE"));
				adminBean.setMB_STAT(rs.getString("MB_STAT"));
				list.add(adminBean);

				System.out.println("Insert successful : " + num);
				// 아이디 찾기 완료
			}

			return list;
		} catch (Exception e) {

			System.out.println("findMyId Error : " + e);
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

	public int myPageAuth(AdminBean member) {
		String sql_1 = "select MB_PW from MEMBER where MB_ID=?";
		int result = -1;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql_1);
			pstmt.setString(1, member.getMB_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("MB_PW").equals(member.getMB_PW())) {
					result = 1; // 일치
				} else {
					result = 0;
				}
			} else {
				result = -1; // 아이디가 존재 x
			}
		} catch (Exception e) {
			System.out.println("myPageAuth Error : " + e);
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

		return result;
	}

	public boolean joinMember(AdminBean member) throws SQLException {
		String sql = "insert into MEMBER values(SEQ_member_num.nextval,?,?,?,?,?,?,?,?,'no')";
		// System.out.println(member.toString());
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
			result = pstmt.executeUpdate();
			con.commit();
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("DB insert error");
			con.rollback();
			e.printStackTrace();
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
		return false;
	}
}
