package iost.json;

import java.util.Map;

import com.google.gson.Gson;

public class Account {
	
	private String name;
	private double balance;
	private long create_time;
	private GasInfo gas_info;
	private RamInfo ram_info;
	private Map<String, Permission> permissions;
	private Map<String, Group> groups;
	private FrozenBalance[] frozen_balances;
	
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
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return the create_time
	 */
	public long getCreateTime() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreateTime(long createTime) {
		this.create_time = createTime;
	}
	/**
	 * @return the gas_info
	 */
	public GasInfo getGasInfo() {
		return gas_info;
	}
	/**
	 * @param gas_info the gas_info to set
	 */
	public void setGasInfo(GasInfo gasInfo) {
		this.gas_info = gasInfo;
	}
	/**
	 * @return the ram_info
	 */
	public RamInfo getRamInfo() {
		return ram_info;
	}
	/**
	 * @param ram_info the ram_info to set
	 */
	public void setRamInfo(RamInfo ramInfo) {
		this.ram_info = ramInfo;
	}
	/**
	 * @return the permissions
	 */
	public Map<String, Permission> getPermissions() {
		return permissions;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Map<String, Permission> permissions) {
		this.permissions = permissions;
	}
	/**
	 * @return the groups
	 */
	public Map<String, Group> getGroups() {
		return groups;
	}
	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
	}
	/**
	 * @return the frozen_balances
	 */
	public FrozenBalance[] getFrozenBalances() {
		return frozen_balances;
	}
	/**
	 * @param frozen_balances the frozen_balances to set
	 */
	public void setFrozenBalances(FrozenBalance[] frozenBalances) {
		this.frozen_balances = frozenBalances;
	}
	
	public static Account getAccount(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, Account.class);
	}
}
