package iost.json;

import com.google.gson.Gson;

public class Token721Metadata {

	private String metadata;
	
	/**
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public static Token721Metadata getToken721Metadata(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, Token721Metadata.class);
	}
}
