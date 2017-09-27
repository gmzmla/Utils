package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

//import com.alibaba.fastjson.JSON;

/**
 * apache HttpClient 工具类
 * <p>
 * 
 * @author hubin
 * @date 2015年5月7日
 * @version 1.0.0
 */
public class HttpClientUtil {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final HttpClient client = HttpClientBuilder.create().build();

	/**
	 * Http Get 请求
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 * @throws Exception
	 */
	public static String executeGet( String url ) throws Exception {
		HttpGet request = new HttpGet(url);

		/**
		 * 设置超时时间
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
		request.setConfig(requestConfig);

		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ( (line = rd.readLine()) != null ) {
			result.append(line);
		}
		return result.toString();
	}


	/**
	 * Http Post 请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public static String executePost( String url, String params ) throws Exception {
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
//		post.setHeader("Token", "+hoT22yGlVqrFkhi+WV26VBrvXGkiZNb48vnrYHJj3K8V7xPcu09bA==");
		post.setHeader("content-type", "application/json");
		if ( params != null ) {
			post.setEntity(new StringEntity(params, "UTF-8"));
		}
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ( (line = rd.readLine()) != null ) {
			result.append(line);
		}
		return result.toString();
	}

	/**
	 * Http Get 请求(每次都是新的client)
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 * @throws Exception
	 */
	public static String executeGetForNewClient( String url ) throws Exception {
		HttpClient newClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		/**
		 * 设置超时时间
		 * setConnectTimeout：设置连接超时时间，单位毫秒。
		 * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		 * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		 */
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
		request.setConfig(requestConfig);

		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = newClient.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ( (line = rd.readLine()) != null ) {
			result.append(line);
		}
		return result.toString();
	}

	public static void main(String[] args) {
        Map<String,Object> params=new HashMap<String,Object>();
        Map<String,String> mapParameters=new HashMap<String,String>();
        Map<String,String> mapClientInfo=new HashMap<String,String>();
        mapParameters.put("Password", "123");
        params.put("Parameters", mapParameters);
        params.put("RequestNo", "123");
        params.put("Url", "http://192.168.4.145:8080/DBCloud/addPasswordError");
        mapClientInfo.put("SaleInfo", "12345678");
        mapClientInfo.put("DeviceInfo", "1");
        params.put("ClientInfo", mapClientInfo);
        params.put("CommandIdentifier", "");
        try {
//            System.out.println(JSON.toJSONString(params));
            
//            http://139.219.231.203:9090/api/income/validincomepwd
            //密码验证
//            System.out.println(HttpClientUtil.executePost("http://192.168.3.64:8880/api/Income/validincomepwd", JSON.toJSONString(params)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
