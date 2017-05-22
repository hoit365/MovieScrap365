package jsp.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * Ŀ�ؼ��� ������ Ŭ���� - JNDI
 */
public class DBConnection 
{
	/**
	 * Ŀ�ؼ� Ǯ�� ���� Ŀ�ؼ� ��ü�� ������ �����Ѵ�.
	 * @return Connection
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, NamingException, 
	ClassNotFoundException{
			Context initCtx = new InitialContext();
			
			//initCtx�� lookup�޼��带 �̿��ؼ� "java:comp/env" �� �ش��ϴ� ��ü�� ã�Ƽ� evnCtx�� ����
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			
			//envCtx�� lookup�޼��带 �̿��ؼ� "jdbc/orcl"�� �ش��ϴ� ��ü�� ã�Ƽ� ds�� ����
			DataSource ds = (DataSource) envCtx.lookup("jdbc/OracleDB");
			
			//getConnection�޼��带 �̿��ؼ� Ŀ�ؼ� Ǯ�� ���� Ŀ�ؼ� ��ü�� ���� conn������ ����
			Connection conn = ds.getConnection();
			return conn;
	}
}	