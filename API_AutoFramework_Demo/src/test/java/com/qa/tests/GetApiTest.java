package com.qa.tests;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
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
	
	@BeforeClass
	public void setup() {
		testBase = new TestBase();
		host = prop.getProperty("HOST") ;
		url = host + "/api/users?page=2";
	}
	
	@Test
	public void testGetAPI() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		chp = restClient.get(url);
		int statusCode = chp.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"response status code in not 200");
		
		//把响应内容存储在字符串对象
		String responseString = EntityUtils.toString(chp.getEntity(),"UTF-8");
		
		JSONObject responseJson = JSONObject.parseObject(responseString);
		
		String result = TestUtil.getValueByJsonpath(responseJson, "data[0]/first_name");
		System.out.println(result);

        Assert.assertEquals(result, "Eve","first name is not Eve");
	}
	
	

}
