package tk.c4se.halt.ih31.nimunimu.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lombok.val;

/**
 * Database configuration.
 * 
 * @author ne_Sachirou
 * 
 */
public class DBConfig {
	// private static final String url =
	// "jdbc:oracle:thin:@TSTDSV03:1521:orcl2";
	// private static final String driver = "oracle.jdbc.driver.OracleDriver";
	// private static final String user = "ora59";
	// private static final String password = "ora59";
	private static final String contextName = "java:comp/env/jdbc/oracleds";

	/**
	 * Connect to database.
	 * 
	 * @return Returns null when fail.
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static Connection getConnection() throws SQLException,
			NamingException {
		val context = new InitialContext();
		val source = (DataSource) context.lookup(contextName);
		return source.getConnection();
	}
}