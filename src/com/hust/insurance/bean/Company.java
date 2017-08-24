package com.hust.insurance.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hust.insurance.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Company {

	private Connection connection;
	private Statement statement;
	private ResultSet set;
	public JSONArray findAll() {
		// TODO Auto-generated method stub
		String sql="select * from company";
		JSONArray infos=new JSONArray();
		try {
			connection=JdbcUtil.getConnection();
			statement=connection.createStatement();
			set=statement.executeQuery(sql);
			while(set.next()){
				JSONObject info=new JSONObject();
				info.put("id", set.getInt("id"));
				info.put("name", set.getString("name"));
				infos.add(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, statement, set);
		}
		return infos;
	}
	
	
	
}
