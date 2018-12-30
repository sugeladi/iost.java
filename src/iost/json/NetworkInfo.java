package iost.json;

public class NetworkInfo {
	
	private String id;
	private int peer_count;
	private PeerInfo[] peer_info;
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
	 * @return the peer_count
	 */
	public int getPeerCount() {
		return peer_count;
	}
	/**
	 * @param peer_count the peer_count to set
	 */
	public void setPeerCount(int peerCount) {
		this.peer_count = peerCount;
	}
	/**
	 * @return the peer_info
	 */
	public PeerInfo[] getPeerInfo() {
		return peer_info;
	}
	/**
	 * @param peer_info the peer_info to set
	 */
	public void setPeerInfo(PeerInfo[] peerInfo) {
		this.peer_info = peerInfo;
	}

}
