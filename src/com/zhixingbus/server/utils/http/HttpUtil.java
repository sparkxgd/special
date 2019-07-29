package com.zhixingbus.server.utils.http;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
/**
 * 
 *Http 请求
 *2015年11月17日 15:26:45
 *
 */
public class HttpUtil{
	private static final String ENCODED="utf-8";
	private static HttpClientBuilder builder = HttpClientBuilder.create();
	/**
	 * post
	 * @param url
	 * @param formparams
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse httpPost(String url, List<NameValuePair> formparams) throws ClientProtocolException, IOException 
	{
		HttpClient client = builder.build();
		HttpResponse response = null;
		HttpPost request = new HttpPost(url);
		request.setEntity(new UrlEncodedFormEntity(formparams, ENCODED));
		response = client.execute(request);
		return response;
	}
	/**
	 * get
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse httpGet(String url) throws ClientProtocolException, IOException
	{
		HttpClient client = builder.build();

		HttpResponse response = null;
		
		HttpGet request = new HttpGet(url);
		
		response = client.execute(request);

		return response;
	}
	/**
	 * Post string
	 * @param url
	 * @param formparams
	 * @return
	 */
	public static String httpPostEntity(String url, List<NameValuePair> formparams)
	{
		String result;
		try
		{
			result=EntityUtils.toString(httpPost(url, formparams).getEntity());
		} catch (IOException e)
		{
			result="";
		}
		return result;
	}
	/**
	 * get String
	 * @param url
	 * @return
	 */
	public static String httpGetEntity(String url)
	{
		String result;
		try
		{
			result=EntityUtils.toString(httpGet(url).getEntity());
		} catch (IOException e)
		{
			result="";
		}
		return result;
	}
	/**
	 * post json
	 * @param url
	 * @param formparams
	 * @return
	 */
	public static JSONObject httpPostResult(String url, List<NameValuePair> formparams)
	{
		JSONObject result = null;
		
		HttpResponse response;
		try {
			response = httpPost(url, formparams);
			if(response.getStatusLine().getStatusCode() == 200)
			{
				String message = null;
				message = EntityUtils.toString(response.getEntity());
				JSONObject obj = new JSONObject(message);
				if(obj.getInt("returnCode") == 0)
				{
					result = obj;
				}
			}
		} catch (IOException e) {
			return result;
		} catch (ParseException e){
			return result;
		} catch (JSONException e){
			return result;
		}
		return result;
	}
}
