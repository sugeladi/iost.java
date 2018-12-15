package provider;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPProvider {
	private Object _host;
	private int _timeout;

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final MediaType TXT_PLAIN = MediaType.parse("text/plain; charset=utf-8");

	/**
	 * http接口访问区块链节点
	 * 
	 * @constructor
	 * @param {string} host - IOST节点的URL
	 * @param {number} timeout - 超时时间，以毫秒计时
	 */
	public HTTPProvider(String host, int timeout) {
		this._host = host;
		this._timeout = timeout;
	}

	/**
	 * @param {string}url - 节点的API
	 * @param {string}json - 参数，以json string表示
	 * @throws IOException
	 * @returns response body as String
	 */
	public void sendPostAsync(String url, String json, Callback responseCallback) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(TXT_PLAIN, json);
		Request request = new Request.Builder().url(_host+url).post(body).build();
		client.newCall(request).enqueue(responseCallback);		
	}
	
	
	/**
	 * @param {string}url - 节点的API
	 * @param {string}json - 参数，以json string表示
	 * @throws IOException
	 * @returns response body as String
	 */
	public String sendPost(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		RequestBody body = RequestBody.create(TXT_PLAIN, jsonTree.getAsJsonObject().toString());
		Request request = new Request.Builder().url(_host+url).header("Connection", "close").post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	/**
	 * @param {string}url - 节点的API
	 * @param {string}json - 参数，以json string表示
	 * @throws IOException
	 * @returns response body as String
	 */
	public String sendGet(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(_host+url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

}
