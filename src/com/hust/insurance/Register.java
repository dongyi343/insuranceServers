package com.hust.insurance;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hust.insurance.util.GetJsonUtil;
import com.hust.insurance.util.MD5Util;
import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONObject;

public class Register extends HttpServlet{

	/**
	 *将用户注册信息存入数据库 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8"); 
		request.setCharacterEncoding("utf-8");
		
		PreparedStatement statement=null;
		JSONObject info=JSONObject.fromObject(GetJsonUtil.getRequestJsonString(request));
		OutputStream outputStream=response.getOutputStream();
		connection=JdbcUtil.getConnection();
		int count=findId();
		String id=String.valueOf(count++);
		for(int i=0;i<5-id.length();i++)
			id='0'+id;
		try {
			String sql="insert into user(name,mobile,password,id_card,id) values(?,?,?,?,?)";
			statement=(PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, info.getString("username"));
			statement.setString(2, info.getString("mobile"));
			statement.setString(3, MD5Util.convertMD5(info.getString("password")));
			statement.setString(4, info.getString("idcard"));
			statement.setString(5, id);
			int result=statement.executeUpdate();
			if(result>0){
				outputStream.write(id.getBytes("utf-8"));
			}else{
				outputStream.write("failure".getBytes("utf-8"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputStream.write("failure".getBytes("utf-8"));
		}finally{
			JdbcUtil.close(connection, statement);
		}
	}
	
	protected int findId() {
		String sql="select id from user order by id limit 1";
		connection=JdbcUtil.getConnection();
		Statement statement=null;
		ResultSet set=null;
		try{
			statement=connection.createStatement();
			set=statement.executeQuery(sql);
			while(set.next()){
				return Integer.valueOf(set.getString(0));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	

}
