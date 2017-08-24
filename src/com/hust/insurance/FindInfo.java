package com.hust.insurance;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.hust.insurance.bean.Business;
import com.hust.insurance.bean.CarType;
import com.hust.insurance.bean.Company;

public class FindInfo extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		OutputStream outputStream=response.getOutputStream();
		String type=request.getParameter("info");
		if(type.equals("company")){
			Company company=new Company();
			JSONArray companies=company.findAll();
			outputStream.write(companies.toString().getBytes("utf-8"));
		}else if(type.equals("carType")){
			CarType carType=new CarType();
			JSONArray info=carType.findAll();
			outputStream.write(info.toString().getBytes("utf-8"));
		}else{
			Business business=new Business();
			JSONArray infos=business.findAll();
			outputStream.write(infos.toString().getBytes("utf-8"));
		}
	}
}
