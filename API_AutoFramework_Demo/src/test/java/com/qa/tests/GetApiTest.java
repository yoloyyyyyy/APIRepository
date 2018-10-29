package com.qa.tests;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;

public class GetApiTest {
	TestBase testBase;
	String host;
	String url;
	
	@BeforeClass
	public void setup() {
		testBase = new TestBase();
		host = testBase.prop.getProperty("HOST") ;
		url = host + "/api/users";
	}
	
	@Test
	public void testGetAPI() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		restClient.get(url);
	}

}
