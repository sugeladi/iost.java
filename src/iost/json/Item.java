package iost.json;

public class Item {

	private String id;
	private boolean is_key_pair;
	private long weight;
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
	 * @return the is_key_pair
	 */
	public boolean isKeyPair() {
		return is_key_pair;
	}
	/**
	 * @param is_key_pair the is_key_pair to set
	 */
	public void setKeyPair(boolean isKeyPair) {
		this.is_key_pair = isKeyPair;
	}
	/**
	 * @return the weight
	 */
	public long getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(long weight) {
		this.weight = weight;
	}
	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	private String permission;
}
