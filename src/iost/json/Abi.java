package iost.json;

public class Abi {

	private String name;
	private String[] args;
	private AmountLimit[] amount_limit;
	
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
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}
	/**
	 * @param args the args to set
	 */
	public void setArgs(String[] args) {
		this.args = args;
	}
	/**
	 * @return the amount_limit
	 */
	public AmountLimit[] getAmountLimits() {
		return amount_limit;
	}
	/**
	 * @param amount_limit the amount_limit to set
	 */
	public void setAmountLimits(AmountLimit[] amountLimits) {
		this.amount_limit = amountLimits;
	}
}
