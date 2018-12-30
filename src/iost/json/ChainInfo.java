package iost.json;

import com.google.gson.Gson;

public class ChainInfo {

	private String net_name;
	private String protocol_version;
	private String head_block;
	private String head_block_hash;
	private String lib_block;
	private String lib_block_hash;
	private String[] witness_list;
	/**
	 * @return the net_name
	 */
	public String getNetName() {
		return net_name;
	}
	/**
	 * @param net_name the net_name to set
	 */
	public void setNetName(String netName) {
		this.net_name = netName;
	}
	/**
	 * @return the protocol_version
	 */
	public String getProtocolVersion() {
		return protocol_version;
	}
	/**
	 * @param protocol_version the protocol_version to set
	 */
	public void setProtocolVersion(String protocolVersion) {
		this.protocol_version = protocolVersion;
	}
	/**
	 * @return the head_block
	 */
	public String getHeadBlock() {
		return head_block;
	}
	/**
	 * @param head_block the head_block to set
	 */
	public void setHeadBlock(String headBlock) {
		this.head_block = headBlock;
	}
	/**
	 * @return the head_block_hash
	 */
	public String getHeadBlockHash() {
		return head_block_hash;
	}
	/**
	 * @param head_block_hash the head_block_hash to set
	 */
	public void setHeadBlockHash(String headBlockHash) {
		this.head_block_hash = headBlockHash;
	}
	/**
	 * @return the lib_block
	 */
	public String getLibBlock() {
		return lib_block;
	}
	/**
	 * @param lib_block the lib_block to set
	 */
	public void setLibBlock(String libBlock) {
		this.lib_block = libBlock;
	}
	/**
	 * @return the lib_block_hash
	 */
	public String getLibBlockHash() {
		return lib_block_hash;
	}
	/**
	 * @param lib_block_hash the lib_block_hash to set
	 */
	public void setLibBlockHash(String libBlockHash) {
		this.lib_block_hash = libBlockHash;
	}
	/**
	 * @return the witness_list
	 */
	public String[] getWitnessList() {
		return witness_list;
	}
	/**
	 * @param witness_list the witness_list to set
	 */
	public void setWitnessList(String[] witnessList) {
		this.witness_list = witnessList;
	}
	/**
	 * @param chainInfo as json 
	 */
	public static ChainInfo getChainInfo(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, ChainInfo.class);
	}
}
