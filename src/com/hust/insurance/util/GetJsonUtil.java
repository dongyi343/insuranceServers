package com.hust.insurance.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class GetJsonUtil {
	/***  
     * ��ȡ request �� json�ַ���������  
     *   
     * @param request  
     * @return : <code>byte[]</code>  
     * @throws IOException  
     */  
    public static String getRequestJsonString(HttpServletRequest request)  
            throws IOException {  
        String submitMehtod = request.getMethod();  //��ȡ���ݴ���ķ���
        // GET  
        if (submitMehtod.equals("GET")) {  
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");  //�������ַ����ı����ʽ��Ĭ�ϵ�iso��Ϊutf-8������%22�滻Ϊ\
        // POST  
        } else {  //post����ֱ�ӷ������������
            return getRequestPostStr(request);  
        }  
    }  
  
    /**        
     * ����:��ȡ post ����� byte[] ����  
     * <pre>  
     * ������  
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
     * ����:��ȡ post ��������  
     * <pre>  
     * ������  
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
