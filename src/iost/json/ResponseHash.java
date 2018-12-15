package iost.json;

import com.google.gson.Gson;

public class ResponseHash {
	private String hash;

	public ResponseHash() {
		super();
	}
	
	public static ResponseHash getResponseHash(String jsonResponse){
		Gson gson = new Gson();
		return gson.fromJson(jsonResponse, ResponseHash.class);
		
	}
	
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
}
