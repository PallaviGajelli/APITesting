package api.TestCases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.base.TestBase;
import api.client.RestClient;

public class DeleteAPITest extends TestBase {
	public DeleteAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	TestBase TestBase;
    String serviceurl;
	String putserviceURL;
	String Url; //= "https://reqres.in/api/users";
	CloseableHttpResponse closablehttpresponse;
	
	
	@BeforeTest
	public void setup() throws IOException  {
		TestBase= new TestBase();
	    serviceurl = prop.getProperty("URL");
	    putserviceURL= prop.getProperty("putserviceURL");
	     
	    Url = serviceurl+putserviceURL;	
	}
	
	@Test
	public void deleteTest() throws ClientProtocolException, IOException {
		System.out.println("*************this is delete API**************");
		RestClient	RestClient = new RestClient();
		
		HashMap<String, String> headerMap= new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		closablehttpresponse=RestClient.delete(Url, headerMap);
		
		int status=closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Statusis -->"+status);
		
		
		
		
		
	}
	

}
