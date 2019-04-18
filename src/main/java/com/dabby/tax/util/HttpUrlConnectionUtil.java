package com.dabby.tax.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: Aaronchen
 * @Date: 2018/8/2 10:47
 */
@Slf4j
public class HttpUrlConnectionUtil {
	
	/**
	 * 向推送程序传送推送数据
	 * @param url
	 * @return
	 */
	public static String sendPost(String url,String obj,int readTime){
		if(readTime==0){
			readTime = 3000;
		}
		StringBuffer sb = new StringBuffer("");
		try {
			log.info("url---"+url);
			URL httpurl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			//connection.setConnectTimeout(URLUtil.CONNECTTIME);//setConnectTimeout：设置连接主机超时（单位：毫秒）
			//setReadTimeout：设置从主机读取数据超时（单位：毫秒）
			connection.setReadTimeout(readTime);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.connect();
			log.info(obj.toString());
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(obj.toString().getBytes("utf-8"));
			out.flush();
			out.close();
			
			//读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			String lines;
			sb = new StringBuffer("");
			
			while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
				log.info("返回数据="+sb.toString());
            }
            reader.close();
            // 断开连接
            connection.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return sb.toString();
	}
	
	
	public static String getResponseFromGet(String urlStr) {
		URL urlGet = null;
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		StringBuilder sb = null;
		try {
			urlGet = new URL(urlStr);
			conn = (HttpURLConnection) urlGet.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			/***************************** 华丽的分割线 *************************/
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"UTF-8"));
			String line = null;
			sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
		} catch (MalformedURLException e) {

			log.error(e.getMessage(),e);
		} catch (IOException e) {

			log.error(e.getMessage(),e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					reader = null;
				}
			}
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
			if (urlGet != null) {
				urlGet = null;
			}
		}
		if (sb != null) {
			return sb.toString();
		}
		return null;
	}
}
