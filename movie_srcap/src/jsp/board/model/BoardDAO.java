package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.common.util.DBConnection;


public class BoardDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static BoardDAO instance;
	DataSource ds;
	private BoardDAO(){
		
		try{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch (Exception ex) {
			System.out.println("DB Connection fail :"+ex);
			return;
		}		
	}
	public static BoardDAO getInstance(){
		if(instance==null)
			instance=new BoardDAO();
		return instance;
	}
	
	public int getSeq()
	{
		int result = 1;
		
		try {
			conn = ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next())	result = rs.getInt(1);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;	
	} // end getSeq
	
	public boolean boardInsert(BoardBean board)
	{
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO MEMBER_BOARD");
			sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
			sql.append(", BOARD_RE_REF, BOARD_COUNT, BOARD_DATE, BOARD_PARENT)");
			sql.append(" VALUES(?,?,?,?,?,?,?,sysdate,?)");
			
			int num = board.getBoard_num();			
			int ref = board.getBoard_re_ref(); 	
			int parent = board.getBoard_parent(); 
			
			if(parent == 0) ref = num;
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBoard_id());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, board.getBoard_count());
			pstmt.setInt(8, parent);

			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); // �Ϸ�� Ŀ��
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} 
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;	
	} // end boardInsert();
	
	
	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt)
    {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        
        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        int start = (Integer)listOpt.get("start"); // 현재 페이지번호
        
        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글목록 전체를 보여줄 때
            if(opt == null)
            {
                // BOARD_RE_REF(그룹번호)의 내림차순 정렬 후 동일한 그룹번호일 때는
                // BOARD_RE_SEQ(답변글 순서)의 오름차순으로 정렬 한 후에
                // 10개의 글을 한 화면에 보여주는(start번째 부터 start+9까지) 쿼리
                // desc : 내림차순, asc : 오름차순 ( 생략 가능 )
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_RE_REF");
                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
                sql.append("FROM");
                sql.append(" (select * from MEMBER_BOARD order by BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setInt(1, start);
                pstmt.setInt(2, start+9);
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_SUBJECT like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_CONTENT like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 글쓴이로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_ID like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                BoardBean board = new BoardBean();
                board.setBoard_num(rs.getInt("BOARD_NUM"));
                board.setBoard_id(rs.getString("BOARD_ID"));
                board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                board.setBoard_content(rs.getString("BOARD_CONTENT"));
                board.setBoard_file(rs.getString("BOARD_FILE"));
                board.setBoard_count(rs.getInt("BOARD_COUNT"));
                board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
                board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                board.setBoard_date(rs.getDate("BOARD_DATE"));
                list.add(board);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return list;
    } // end getBoardList

	public int getBoardListCount(HashMap<String, Object> listOpt)
	{
		int result = 0;
		String opt = (String)listOpt.get("opt");
		String condition = (String)listOpt.get("condition");
		
		try {
			conn = ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			if(opt == null)	
			{
				sql.append("select count(*) from MEMBER_BOARD");
				pstmt = conn.prepareStatement(sql.toString());
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("0")) 
			{
				sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+condition+'%');
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("1")) 
			{
				sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+condition+'%');
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("2")) 
			{
				sql.append("select count(*) from MEMBER_BOARD ");
				sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+condition+'%');
				pstmt.setString(2, '%'+condition+'%');
				
				sql.delete(0, sql.toString().length());
			}
			else if(opt.equals("3")) 
			{
				sql.append("select count(*) from MEMBER_BOARD where BOARD_ID like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+condition+'%');
				
				sql.delete(0, sql.toString().length());
			}
			
			rs = pstmt.executeQuery();
			if(rs.next())	result = rs.getInt(1);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;
	} 
	
	

	public BoardBean getDetail(int boardNum)
	{	
		BoardBean board = null;
		
		try {
			conn = ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from MEMBER_BOARD where BOARD_NUM = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				board = new BoardBean();
				board.setBoard_num(boardNum);
				board.setBoard_id(rs.getString("BOARD_ID"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_count(rs.getInt("BOARD_COUNT"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_date(rs.getDate("BOARD_DATE"));
				board.setBoard_parent(rs.getInt("BOARD_PARENT"));
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return board;
	} // end getDetail()
	
	public boolean updateCount(int boardNum)
	{
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			
			// �ڵ� Ŀ���� false�� �Ѵ�.
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("update MEMBER_BOARD set BOARD_COUNT = BOARD_COUNT+1 ");
			sql.append("where BOARD_NUM = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit();
			}	
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;
	} // end updateCount
	
	
	public String getFileName(int boardNum)
	{
		String fileName = null;
		
		try {
			conn = ds.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT BOARD_FILE from MEMBER_BOARD where BOARD_NUM=?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) fileName = rs.getString("BOARD_FILE");
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return fileName;
	} // end getFileName
		
	public boolean deleteBoard(int boardNum) 
	{
		boolean result = false;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM MEMBER_BOARD");
			sql.append(" WHERE BOARD_NUM IN");
			sql.append(" (SELECT BOARD_NUM");
			sql.append(" FROM MEMBER_BOARD");
			sql.append(" START WITH BOARD_NUM = ?");
			sql.append(" CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); // �Ϸ�� Ŀ��
			}	
			
		} catch (Exception e) {
			try {
				conn.rollback(); // ������ �ѹ�
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	} // end deleteBoard
	
	public boolean updateBoard(BoardBean border) 
	{
		boolean result = false;
		
		try{
			conn = ds.getConnection();
			conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE MEMBER_BOARD SET");
			sql.append(" BOARD_SUBJECT=?");
			sql.append(" ,BOARD_CONTENT=?");
			sql.append(" ,BOARD_FILE=?");
			sql.append(" ,BOARD_DATE=SYSDATE ");
			sql.append("WHERE BOARD_NUM=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, border.getBoard_subject());
			pstmt.setString(2, border.getBoard_content());
			pstmt.setString(3, border.getBoard_file());
			pstmt.setInt(4, border.getBoard_num());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); // �Ϸ�� Ŀ��
			}
			
		} catch (Exception e) {
			try {
				conn.rollback(); // ������ �ѹ�
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
	
		close();
		return result;
	} // end updateBoard
	
	
	private void close()
	{
		try {
			if ( pstmt != null ){ pstmt.close(); pstmt=null; }
			if ( conn != null ){ conn.close(); conn=null;	}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	} // end close()
}
