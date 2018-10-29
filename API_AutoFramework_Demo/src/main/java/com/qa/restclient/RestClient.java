package com.qa.restclient;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestClient {
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = client.execute(httpGet);
		

		//拿到Http响应状态码，例如和200,404,500去比较
		int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("response status code -->"+responseStatusCode);
		

		//把响应内容存储在字符串对象
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		

		//创建Json对象，把上面字符串序列化成Json对象
		JSONObject responseJSON = JSON.parseObject(responseString);
		System.out.println("response status code -->"+responseJSON);
		
		Header[] headers = httpResponse.getAllHeaders();
		HashMap<String, String> hm = new HashMap<String, String>();
		for(Header header : headers) {
			hm.put(header.getName(), header.getValue());
		}

		System.out.println("response headers -->"+ hm);
		
	}

}
