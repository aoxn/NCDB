package com.space.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBTools {

	private static Properties proper = null;
	public static Properties getConfiguration(){
		if(proper==null)proper = loadPropertity();
		return proper;
	}
	public static Properties loadConfiguration(){
		return getConfiguration();
	}
	private static Properties loadPropertity(){
		Properties p = new Properties();
		InputStream f=p.getClass().getResourceAsStream("/config.properties");
		try {
			System.out.println();
			p.load(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(getDriverIN());
			conn = DriverManager.getConnection(getUrlIN(), getUsernameIN(), getPasswordIN());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConn(Connection conn) throws SQLException {
		try {
			if (conn == null) {
				System.out
						.println("connection is nul(######################log:connection ЮЊПе)");
			} else if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeState(Statement state) throws SQLException {
		try {
			if (state == null) {
				System.out
						.println("statement is nul(######################log:statement ЮЊПе)");
			} else if (!state.isClosed()) {
				state.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void closeRs(ResultSet rs) throws SQLException {
		try {
			if (rs == null) {
				System.out
						.println("recordset is nul(######################log:statement ЮЊПе)");
			} else if (!rs.isClosed()) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getDriverIN() {
		return DBTools.getConfiguration().getProperty("db.driver");
	}
	public static String getUrlIN() {
		return DBTools.getConfiguration().getProperty("db.url");
	}
	public static String getUsernameIN() {
		return DBTools.getConfiguration().getProperty("db.username");
	}
	public static String getPasswordIN() {
		return DBTools.getConfiguration().getProperty("db.password");
	}
	public Properties getProper() {
		return proper;
	}

}
