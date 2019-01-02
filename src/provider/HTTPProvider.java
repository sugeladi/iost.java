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

/**
 * provider of http, using ok http
 */
public class HTTPProvider {
	private String host;
	private int timeout;
	
		
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final MediaType TXT_PLAIN = MediaType.parse("text/plain; charset=utf-8");

	

	/**
	 * @param host
	 * @param timeout
	 * @param intervalInMillis
	 * @param times
	 */
	public HTTPProvider(String host, int timeout) {
		this.host = host;
		this.timeout = timeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
     * 异步发送post
     *
	 * @param url - 节点的API
	 * @param json - 参数，以json string表示
	 */
	public void sendPostAsync(String url, String json, Callback responseCallback) {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(TXT_PLAIN, json);
		Request request = new Request.Builder().url(host +url).post(body).build();
		client.newCall(request).enqueue(responseCallback);		
	}
	
	
	/**
     * 同步发送post
     *
	 * @param url - 节点的API
	 * @param json - 参数，以json string表示
	 * @throws IOException - 发送失败
	 * @return response body as String
	 */
	public String sendPost(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		RequestBody body = RequestBody.create(TXT_PLAIN, jsonTree.getAsJsonObject().toString());
		Request request = new Request.Builder().url(host +url).header("Connection", "close").post(body).build();
		Response response = client.newCall(request).execute();
        if (response.code() != 200) throw new IOException(response.body().string());
        return response.body().string();
	}

	/**
     * 同步发送get
     *
	 * @param url - 节点的API
	 * @throws IOException - 失败
	 * @return response body as String
	 */
	public String sendGet(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(host +url).build();
		Response response = client.newCall(request).execute();
		if (response.code() != 200) throw new IOException(response.body().string());
		return response.body().string();
	}
}
