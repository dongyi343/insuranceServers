package com.hust.insurance.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUtil {

	public boolean saveFile(HttpServletRequest request,HttpServletResponse response,String tempPth){
		try{
			DiskFileItemFactory factory=new DiskFileItemFactory();
			File tempFile=new File(tempPth);
			factory.setSizeThreshold(1024*100);
			factory.setRepository(tempFile);
			ServletFileUpload upload=new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			//设置单个文件的最大值，1M
			upload.setFileSizeMax(1024*1024);
			//设置文件总量的最大值
			upload.setSizeMax(1024*1024*10);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}
	
	
}
