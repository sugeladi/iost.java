package iost.json;

import com.google.gson.Gson;

public class Contract {

	private String id;
	private String code;
	private String language;
	private String version;
	private Abi[] abis;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the abis
	 */
	public Abi[] getAbis() {
		return abis;
	}
	/**
	 * @param abis the abis to set
	 */
	public void setAbis(Abi[] abis) {
		this.abis = abis;
	}
	
	public static Contract getContract(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, Contract.class);
	}
}
