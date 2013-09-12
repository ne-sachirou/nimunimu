package tk.c4se.hal.ih31.nimunimu.config;

import java.sql.*;

/**
 * Database configuration.
 * @author ne_Sachirou
 *
 */
public class DBConfig {
	private static final String url = "jdbc:oracle:thin:@TSTDSV03:1521:orcl2";
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String user = "ora59";
	private static final String password = "ora59";

	/**
	 * Connect to database.
	 * @return Returns null when fail.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(driver);
		Connection connection = null;
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
