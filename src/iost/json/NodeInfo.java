package iost.json;

import com.google.gson.Gson;

public class NodeInfo {

	private String build_time;
	private String git_hash;
	private String mode;
	private NetworkInfo network;
	
	/**
	 * @return the build_time
	 */
	public String getBuildTime() {
		return build_time;
	}
	/**
	 * @param build_time the build_time to set
	 */
	public void setBuildTime(String buildTime) {
		this.build_time = buildTime;
	}
	/**
	 * @return the git_hash
	 */
	public String getGitHash() {
		return git_hash;
	}
	
	public static NodeInfo getNodeInfo(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, NodeInfo.class);
    }
	/**
	 * @param git_hash the git_hash to set
	 */
	public void setGitHash(String gitHash) {
		this.git_hash = gitHash;
	}
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the network
	 */
	public NetworkInfo getNetwork() {
		return network;
	}
	/**
	 * @param network the network to set
	 */
	public void setNetwork(NetworkInfo network) {
		this.network = network;
	}
}
