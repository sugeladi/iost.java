package iost.json;

public class Permission {

	private String name;
	private Group[] groups;
	private Item[] items;
	private long threshold;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the groups
	 */
	public Group[] getGroups() {
		return groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Group[] groups) {
		this.groups = groups;
	}
	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(Item[] items) {
		this.items = items;
	}
	/**
	 * @return the threshold
	 */
	public long getThreshold() {
		return threshold;
	}
	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}
}
