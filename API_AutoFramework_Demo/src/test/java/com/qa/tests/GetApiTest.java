package com.qa.tests;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse chp;

	private static final org.apache.log4j.Logger Log = Logger.getLogger(GetApiTest.class);
	
	@BeforeClass
	public void setup() {
		testBase = new TestBase();
		host = prop.getProperty("HOST") ;
		url = host + "/api/users?page=2";
	}
	
	@Test
	public void testGetAPI() throws ClientProtocolException, IOException {
		Log.info("开始执行用例...");
		restClient = new RestClient();
		chp = restClient.get(url);
		Log.info("测试响应状态码是否是200");
		int statusCode = restClient.getStatusCode(chp);
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200);
		
		//把响应内容存储在字符串对象
		JSONObject responseJson = restClient.getJSONObject(chp);
		
		String result = TestUtil.getValueByJsonpath(responseJson, "data[0]/first_name");
		Log.info("执行JSON解析，解析的内容是 "+result);
		Log.info("接口内容响应断言");
        Assert.assertEquals(result, "Eve","first name is not Eve");
        Log.info("测试用例结束...");
	}
	
	

}
