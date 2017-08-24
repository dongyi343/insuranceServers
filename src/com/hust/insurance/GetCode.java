package com.hust.insurance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @author stacy
 *生成以及发送验证码界面
 */
public class GetCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int code=(int)(Math.random()*9+1)*100000;
		String mobile=request.getParameter("mobile");
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		post.addRequestHeader("Content-Type",
		"application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", mobile), // 注册的用户名
		new NameValuePair("Key", mobile), // 注册成功后,登录网站使用的密钥
		new NameValuePair("smsMob", mobile), // 手机号码
		new NameValuePair("smsText", "【保醒通】您的验证码为"+code+"，请不要随意告诉别人！") };
		post.setRequestBody(data);
		  
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
		System.out.println(h.toString());
		}
		 new String(post.getResponseBodyAsString().getBytes("utf-8"));
		post.releaseConnection();
	}
	
}
