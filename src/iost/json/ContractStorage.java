package iost.json;

import com.google.gson.Gson;

public class ContractStorage {
	
	private String pubkey;
	private String loc;
	private String url;
	private String netId;
	private boolean online;
	private String registerFee;
	
	/**
	 * @return the pubkey
	 */
	public String getPubkey() {
		return pubkey;
	}
	/**
	 * @param pubkey the pubkey to set
	 */
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the netId
	 */
	public String getNetId() {
		return netId;
	}
	/**
	 * @param netId the netId to set
	 */
	public void setNetId(String netId) {
		this.netId = netId;
	}
	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}
	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}
	/**
	 * @return the registerFee
	 */
	public String getRegisterFee() {
		return registerFee;
	}
	/**
	 * @param registerFee the registerFee to set
	 */
	public void setRegisterFee(String registerFee) {
		this.registerFee = registerFee;
	}
	
	public static ContractStorage getContractStorage(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, ContractStorage.class);
	}

}
