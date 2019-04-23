package api.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import api.base.TestBase;
import api.client.RestClient;
import api.util.users;

public class PostAPITest extends TestBase {

	public PostAPITest() throws IOException {
		super();
	}

	TestBase TestBase;
	RestClient RestClient;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closablehttpresponse;

	@BeforeMethod
	public void setup() throws IOException {

		TestBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = serviceUrl + apiUrl;
	}

	@Test
	public void postAPITest() throws ClientProtocolException, IOException {

		RestClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		users users = new users("leena", "AutomationTester");

		mapper.writeValue(new File(
				"C:\\Users\\sanjaygajelli\\eclipse-workspace\\APITesting\\src\\main\\java\\api\\data\\users.json"),
				users);

		// marshaling java object to jason in string
		String JsonFormat = mapper.writeValueAsString(users);
		System.out.println("Marshling-->" + JsonFormat);

		closablehttpresponse = RestClient.post(url, JsonFormat, headerMap);
		// Status
		int Status = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Satus code is -->" + Status);

		// response
		String Response = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
		JSONObject JsonResponse = new JSONObject(Response);
		System.out.println("Json Response is-->" + JsonResponse);

		// header
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header headers : headersArray) {
			allheaders.put(headers.getName(), headers.getValue());
		}
		System.out.println("Headers array==>" + allheaders);

		// UnMarshaling json to java object:
		users usersResObj = mapper.readValue(Response, users.class); // actual users object
		System.out.println("Unmashaling-->" + usersResObj);

	}

}
