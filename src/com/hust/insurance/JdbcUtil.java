package com.hust.insurance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	/**
	 * 数据库操作工具包
	 */
//	private static String url = "jdbc:mysql://120.26.243.65:3306/axlogistics?useSSL=true";
//	private static String user = "root";
//	private static String password = "hust311";
	private static String url = "jdbc:mysql://127.0.0.1:3306/insurance?useSSL=true";
	private static String user = "root";
	private static String password = "root";
	static
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		try 
		{
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void close(Connection conn,PreparedStatement stmt)
	{
		if(stmt!=null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void close(Connection conn,Statement stmt,ResultSet rs)
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
