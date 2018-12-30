package iost.json;

public class GasInfo {

	private double current_total;
	private double transferable_gas;
	private double pledge_gas;
	private double increase_speed;
	private double limit;
	private PledgeInfo[] pledged_info;
	
	/**
	 * @return the current_total
	 */
	public double getCurrentTotal() {
		return current_total;
	}
	/**
	 * @param current_total the current_total to set
	 */
	public void setCurrentTotal(double currentTotal) {
		this.current_total = currentTotal;
	}
	/**
	 * @return the transferable_gas
	 */
	public double getTransferableGas() {
		return transferable_gas;
	}
	/**
	 * @param transferable_gas the transferable_gas to set
	 */
	public void setTransferableGas(double transferableGas) {
		this.transferable_gas = transferableGas;
	}
	/**
	 * @return the pledge_gas
	 */
	public double getPledgeGas() {
		return pledge_gas;
	}
	/**
	 * @param pledge_gas the pledge_gas to set
	 */
	public void setPledgeGas(double pledgeGas) {
		this.pledge_gas = pledgeGas;
	}
	/**
	 * @return the increase_speed
	 */
	public double getIncreaseSpeed() {
		return increase_speed;
	}
	/**
	 * @param increase_speed the increase_speed to set
	 */
	public void setIncreaseSpeed(double increaseSpeed) {
		this.increase_speed = increaseSpeed;
	}
	/**
	 * @return the limit
	 */
	public double getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(double limit) {
		this.limit = limit;
	}
	/**
	 * @return the pledged_info
	 */
	public PledgeInfo[] getPledgeInfos() {
		return pledged_info;
	}
	/**
	 * @param pledged_info the pledged_info to set
	 */
	public void setPledgeInfos(PledgeInfo[] pledgeInfos) {
		this.pledged_info = pledgeInfos;
	}
}
