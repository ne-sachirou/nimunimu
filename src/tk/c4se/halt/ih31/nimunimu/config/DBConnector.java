package tk.c4se.halt.ih31.nimunimu.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import lombok.val;

/**
 * Database configuration and connection.
 * 
 * @author ne_Sachirou
 */
public class DBConnector {
	// <Resource auth="Container" defaultAutoCommit="false"
	// driverClassName="oracle.jdbc.driver.OracleDriver" maxActive="4"
	// maxIdle="4" name="jdbc/nimunimu" password="ora103" removeAbandoned="true"
	// type="javax.sql.DataSource" url="jdbc:oracle:thin:@TSTDSV03:1521:orcl2"
	// username="ora103" />
	private static final String contextName = "java:comp/env/jdbc/nimunimu";

	/**
	 * Connect to database.
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public static Connection getConnection() throws DBAccessException {
		@val
		javax.sql.DataSource source;
		try {
			source = (DataSource) new InitialContext().lookup(contextName);
			return source.getConnection();
		} catch (NamingException | SQLException e) {
			throw new DBAccessException();
		}
	}
}