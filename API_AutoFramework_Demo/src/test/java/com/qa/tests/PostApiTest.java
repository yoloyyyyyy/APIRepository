package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
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

public class PostApiTest extends TestBase {
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse chp;
	
	@BeforeClass
	public void setup() {
		testBase = new TestBase();
		host = testBase.prop.getProperty("HOST") ;
		url = host + "/api/users";
	}
	
	@Test
	public void testPostAPI() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		//准备请求头信息
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("Content-Type", "application/json");
		//请求参数json字符串
		User user = new User("yolo", "it");
		String jString = JSON.toJSONString(user);
		System.out.println(jString);
		
		chp = restClient.post(url, jString, hMap);
		int statusCode = chp.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201,"status code is not 200 ");
		
		//把响应内容存储在字符串对象
		String responseString = EntityUtils.toString(chp.getEntity(),"UTF-8");
		
		JSONObject responseJson = JSONObject.parseObject(responseString);
		
		String name = TestUtil.getValueByJsonpath(responseJson, "name");
		String job = TestUtil.getValueByJsonpath(responseJson, "job");
		
		Assert.assertEquals(name, "yolo","name is not yolo");
		Assert.assertEquals(job, "it","job is not it");
		
	}

}
