package iost.json;

public class ContractStorageRequest {
	
	private String field;
	private String id;
	private String key;
	private boolean by_longest_chain;
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the by_longest_chain
	 */
	public boolean isBy_longest_chain() {
		return by_longest_chain;
	}
	/**
	 * @param by_longest_chain the by_longest_chain to set
	 */
	public void setBy_longest_chain(boolean by_longest_chain) {
		this.by_longest_chain = by_longest_chain;
	}
	
}
