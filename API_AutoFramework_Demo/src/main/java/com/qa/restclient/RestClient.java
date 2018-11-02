package com.qa.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class RestClient {

	private final static Logger Log = Logger.getLogger(RestClient.class);
	
	
	/**
	 * 不带请求头的get方法封装
	 * @param url
	 * @return 返回响应对象
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		Log.info("开始发送get请求...");
		CloseableHttpResponse httpResponse = client.execute(httpGet);
		Log.info("发送请求成功！开始得到响应对象。");
		return httpResponse;

	}
	
	/**
	 * 带请求头信息的get方法
	 * @param url
	 * @param headermap，键值对形式
	 * @return 返回响应对象
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url,HashMap<String, String> hMap) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		Log.info("开始发送get请求...");
		for(Map.Entry<String, String> entry : hMap.entrySet()) {
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse = client.execute(httpGet);
		Log.info("发送请求成功！开始得到响应对象。");
		return httpResponse;

	}
	
	/**
	 * 封装post方法
	 * @param url
	 * @param entityString，其实就是设置请求json参数
	 * @param headermap，带请求头
	 * @return 返回响应对象
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse post(String url,String stringEntity ,HashMap<String, String> hMap) throws ClientProtocolException, IOException {

        //创建一个可关闭的HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();

        //创建一个HttpPost的请求对象
		HttpPost httpPost = new HttpPost(url);
		Log.info("开始发送post请求...");
		

        //设置payload
		httpPost.setEntity(new StringEntity(stringEntity));

        //加载请求头到httppost对象
		for(Map.Entry<String, String> entry : hMap.entrySet()) {
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse = client.execute(httpPost);
		Log.info("发送请求成功！开始得到响应对象。");
		return httpResponse;
	}
	

	
	/**
	 * 封装 put请求方法，参数和post方法一样
	 * @param url
	 * @param entityString，这个主要是设置payload,一般来说就是json串
	 * @param headerMap，带请求的头信息，格式是键值对，所以这里使用hashmap
	 * @return 返回响应对象
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse put(String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		Log.info("开始发送put请求...");
		httpput.setEntity(new StringEntity(entityString));
	
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpput.addHeader(entry.getKey(), entry.getValue());
		}
		//发送put请求
		CloseableHttpResponse httpResponse = httpclient.execute(httpput);
		Log.info("发送请求成功！开始得到响应对象。");
		return httpResponse;
	}
	
	/**
	 * 封装 delete请求方法，参数和get方法一样
	 * @param url， 接口url完整地址
	 * @return，返回一个response对象，方便进行得到状态码和json解析动作
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
			
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpdel = new HttpDelete(url);
		Log.info("开始发送delete请求...");
		//发送put请求
		CloseableHttpResponse httpResponse = httpclient.execute(httpdel);
		Log.info("发送请求成功！开始得到响应对象。");
		return httpResponse;
	}

	//获取状态码
	public int getStatusCode(CloseableHttpResponse closeableHttpResponse) {
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Log.info("获取状态码...");
		return statusCode;
	}
	
	//获取json对象
	
	public JSONObject getJSONObject(CloseableHttpResponse closeableHttpResponse) throws ParseException, IOException{
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON = JSON.parseObject(responseString);
		Log.info("获取json对象...");
		return responseJSON;
	}
/*		//把响应内容存储在字符串对象
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		

		//创建Json对象，把上面字符串序列化成Json对象
		JSONObject responseJSON = JSON.parseObject(responseString);
		System.out.println("response status api -->"+responseJSON);
		
		Header[] headers = httpResponse.getAllHeaders();
		HashMap<String, String> hm = new HashMap<String, String>();
		for(Header header : headers) {
			hm.put(header.getName(), header.getValue());
		}

		System.out.println("response headers -->"+ hm);*/
		


}
