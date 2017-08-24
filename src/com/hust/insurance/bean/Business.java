package com.hust.insurance.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hust.insurance.JdbcUtil;

public class Business {

	/**
	 * 该类中写所有对business表格进行的操作
	 */
	private Connection connection;
	private ResultSet set;
	private Statement statement;
	
	//查找表中所有的数据
	public JSONArray findAll(){
		String sql="select * from business_insurance";
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
