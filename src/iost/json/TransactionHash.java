package iost.json;

import com.google.gson.Gson;

public class TransactionHash {
	private String hash;

	public TransactionHash() {
		super();
	}
	
	public static TransactionHash getResponseHash(String jsonResponse){
		Gson gson = new Gson();
		return gson.fromJson(jsonResponse, TransactionHash.class);
		
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
