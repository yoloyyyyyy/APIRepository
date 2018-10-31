package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.data.User;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class PutApiTest extends TestBase {
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
		host = testBase.prop.getProperty("HOST");
		url = host + "/api/users/2";
		
	}
	
	@Test
	public void putTest() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		
		//对象转换成Json字符串
		User user = new User("Anthony","automation tester");
		String userJsonString = JSON.toJSONString(user);
		//System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.put(url, userJsonString, headermap);
		
		//验证状态码是不是200
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"response status code is not 200");
		
		
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
		JSONObject responseJson = JSON.parseObject(responseString);
		String jobString = TestUtil.getValueByJsonpath(responseJson, "job");
		System.out.println(jobString);
		Assert.assertEquals(jobString, "automation tester","job is not same");


	}
}
