package iost.json;

public class ContractStorageFields {
	
	private String id;
	private String fields;
	private boolean by_longest_chain;
	
	public ContractStorageFields() {}
	/**
	 * @param id
	 * @param fields
	 * @param by_longest_chain
	 */
	public ContractStorageFields(String id, String field, boolean by_longest_chain) {
		this.id = id;
		this.fields = field;
		this.by_longest_chain = by_longest_chain;
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
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(String field) {
		this.fields = field;
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
