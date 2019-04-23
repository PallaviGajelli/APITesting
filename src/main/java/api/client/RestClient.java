package api.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// get without header
	public void get1(String Url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(Url); // http get request
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget); // hit the GET URL

		// get status code
		int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code is--->" + statusCode);

		// get jason response
		String response = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
		JSONObject responseJason = new JSONObject(response);
		System.out.println("Response JASON fron API-->" + responseJason);

		// get headers
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers array==>" + allHeaders);

	}

	// get without header
	public CloseableHttpResponse get(String Url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(Url); // http get request
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget); // hit the GET URL

		return closablehttpresponse;
	}

	// get with header
	public CloseableHttpResponse get(String Url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(Url); // http get request

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget); // hit the GET URL

		return closablehttpresponse;
	}

	//Post method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString));

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpPost);
		return closablehttpresponse;

	}
	

	//put method
	public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpPut);
		return closablehttpresponse;

	}
	
	//delete method
		public CloseableHttpResponse delete(String url,HashMap<String, String> headerMap)
				throws ClientProtocolException, IOException {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpDelete httpDelete = new HttpDelete(url);
			

			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpDelete.addHeader(entry.getKey(), entry.getValue());
			}

			CloseableHttpResponse closablehttpresponse = httpclient.execute(httpDelete);
			return closablehttpresponse;

		}
	

}
