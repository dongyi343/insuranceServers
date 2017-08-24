package com.hust.insurance.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class GetJsonUtil {
	/***  
     * 获取 request 中 json字符串的内容  
     *   
     * @param request  
     * @return : <code>byte[]</code>  
     * @throws IOException  
     */  
    public static String getRequestJsonString(HttpServletRequest request)  
            throws IOException {  
        String submitMehtod = request.getMethod();  //获取数据传输的方法
        // GET  
        if (submitMehtod.equals("GET")) {  
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");  //将传输字符串的编码格式由默认的iso变为utf-8，并将%22替换为\
        // POST  
        } else {  //post方法直接返回请求的内容
            return getRequestPostStr(request);  
        }  
    }  
  
    /**        
     * 描述:获取 post 请求的 byte[] 数组  
     * <pre>  
     * 举例：  
     * </pre>  
     * @param request  
     * @return  
     * @throws IOException        
     */  
    public static byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
  
    /**        
     * 描述:获取 post 请求内容  
     * <pre>  
     * 举例：  
     * </pre>  
     * @param request  
     * @return  
     * @throws IOException        
     */  
    public static String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
    }  
}
