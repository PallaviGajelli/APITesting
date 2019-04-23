package api.TestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.base.TestBase;
import api.client.RestClient;
import api.util.TestUtil;


public class GetApiTest extends TestBase{

	public GetApiTest() throws IOException {
		super();
	}

	TestBase TestBase;
    String serviceurl;
	String apiurl;
	String Url; //= "https://reqres.in/api/users";
	CloseableHttpResponse closablehttpresponse;
	
	
	@BeforeTest
	public void setup() throws IOException  {
		TestBase= new TestBase();
	    serviceurl = prop.getProperty("URL");
	    apiurl= prop.getProperty("serviceURL");
	     
	    Url = serviceurl+apiurl;	
	}
	
	@Test
	public void simpleGetTest() throws ClientProtocolException, IOException {
		System.out.println("*************this is simple get API**************");
		RestClient	RestClient = new RestClient();
		RestClient.get1(Url);
		
	}
	
	@Test
	public void GetTestwithoutHeader() throws ClientProtocolException, IOException {
	System.out.println("*************this is GetTestwithoutHeader asserting jason objects and array**************");
		RestClient	RestClient = new RestClient();
		
		closablehttpresponse= RestClient.get(Url);
	
		int statusCode= closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code is--->"+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"status code is not 200");
		
		String response = EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
		JSONObject responseJason = new JSONObject(response);
		System.out.println("Response JASON fron API-->"+responseJason);
		
		String s =TestUtil.getValueByJPath(responseJason, "/per_page");
		System.out.println(s);
		String last_name =TestUtil.getValueByJPath(responseJason, "/data[0]/last_name");
		String id =TestUtil.getValueByJPath(responseJason, "/data[0]/id");
		String avatar =TestUtil.getValueByJPath(responseJason, "/data[0]/avatar");
		String first_name =TestUtil.getValueByJPath(responseJason, "/data[0]/first_name");
		
		System.out.println("lastname is: "+last_name);
		System.out.println("id is: "+id);
		System.out.println("avatar is :"+avatar);
		System.out.println("first name is: "+first_name);
		
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header:headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}System.out.println("Headers array==>"+allHeaders);
		
	}
	
	@Test
	public void GetTestwithHeader() throws ClientProtocolException, IOException {
		System.out.println("*************this is GetTestwithHeader asserting jason objects and array**************");
		HashMap<String, String> headerMap= new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		RestClient	RestClient = new RestClient();
		
		closablehttpresponse= RestClient.get(Url,headerMap);
	
		int statusCode= closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code is--->"+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"status code is not 200");
		
		String response = EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
		JSONObject responseJason = new JSONObject(response);
		System.out.println("Response JASON fron API-->"+responseJason);
		
		String s =TestUtil.getValueByJPath(responseJason, "/per_page");
		System.out.println(s);
		String last_name =TestUtil.getValueByJPath(responseJason, "/data[0]/last_name");
		String id =TestUtil.getValueByJPath(responseJason, "/data[0]/id");
		String avatar =TestUtil.getValueByJPath(responseJason, "/data[0]/avatar");
		String first_name =TestUtil.getValueByJPath(responseJason, "/data[0]/first_name");
		
		System.out.println("lastname is: "+last_name);
		System.out.println("id is: "+id);
		System.out.println("avatar is :"+avatar);
		System.out.println("first name is: "+first_name);
		
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header:headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}System.out.println("Headers array==>"+allHeaders);
		
	}
	
	
	
}
