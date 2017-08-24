package com.hust.insurance;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hust.insurance.util.GetJsonUtil;
import com.hust.insurance.util.MD5Util;

public class Login extends HttpServlet{

	/**
	 * 实现用户登录验证
	 */
	private static final long serialVersionUID = 1L;


@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.setContentType("text/html");
	response.setCharacterEncoding("utf-8"); 
	request.setCharacterEncoding("utf-8");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	OutputStream out = response.getOutputStream();
	
	JSONObject info=JSONObject.fromObject(GetJsonUtil.getRequestJsonString(request));
	String sql="select password,id from user where mobile='"+info.getString("username")+"'";
	try {
		conn = JdbcUtil.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		 while(rs.next())
		 {
			 if(MD5Util.convertMD5(rs.getString(1)).equals(info.getString("password")))
			 {
				out.write(rs.getString("id").getBytes("utf-8"));
			 }else{
				 out.write("failure".getBytes("utf-8"));
			 }
		 }
	} catch (SQLException e) {		
		e.printStackTrace();
		throw new RuntimeException(e);
	}finally{
		JdbcUtil.close(conn, stmt, rs);
		out.close();
	}
}

}
