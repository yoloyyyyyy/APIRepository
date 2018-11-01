package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.qa.data.RegisitPara;
import com.qa.data.User;
import com.qa.restclient.RestClient;
import com.qa.util.ExcelHandle;

public class RegistTest {
	String host="http://sg-en-web-api.65emall.net";
	String filepath = "C:\\Users\\yolo\\IdeaProjects\\ModelProject\\eztest\\src\\main\\java\\data\\auto01.xlsx";
	RestClient restClient;
	CloseableHttpResponse chp;
	ExcelHandle handle;
	
    @DataProvider(name = "postdata")
    public Object[][] getExcelData(){
    	handle = new ExcelHandle();
       return handle.readExcel(filepath,0);
    }
    
    @Test(dataProvider = "postdata")
    public void testRegist(String url,String region,String mail,String password,String nickname) throws ClientProtocolException, IOException{
        RegisitPara para = new RegisitPara(url,region,mail,nickname);
		restClient = new RestClient();
		//准备请求头信息
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("Content-Type", "application/json");
		//请求参数json字符串
		String jString = JSON.toJSONString(para);
		System.out.println(jString);
		
        chp = restClient.post(host+url, jString, hMap);
    }
}
