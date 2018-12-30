package iost.json;

import com.google.gson.Gson;

public class TokenBalance {

	private double balance;
	private FrozenBalance[] frozen_balances;
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
	
	public static TokenBalance getTokenBalance(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, TokenBalance.class);
	}
}
