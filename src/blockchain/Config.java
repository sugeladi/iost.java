package blockchain;

public class Config {

	private long gasRatio, gasLimit, delay,expiration;
	/**
	 * @param gasRatio
	 * @param gasLimit
	 * @param delay
	 * @param expiration
	 * @param defaultLimit
	 */
	public Config(long gasRatio, long gasLimit, long delay, long expiration, String defaultLimit) {
		this.gasRatio = gasRatio;
		this.gasLimit = gasLimit;
		this.delay = delay;
		this.expiration = expiration;
		this.defaultLimit = defaultLimit;
	}

	private String defaultLimit;

	/**
	 * @return the expiration
	 */
	public long getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the defaultLimit
	 */
	public String getDefaultLimit() {
		return defaultLimit;
	}

	/**
	 * @param defaultLimit the defaultLimit to set
	 */
	public void setDefaultLimit(String defaultLimit) {
		this.defaultLimit = defaultLimit;
	}

	public Config() {
		super();
		gasRatio = 1;
		gasLimit = 2000000;
		delay = 0;
		defaultLimit = "unlimited";
		expiration = 90;
	}



	public long getGasRatio() {
		return gasRatio;
	}

	public void setGasRatio(long gasRatio) {
		this.gasRatio = gasRatio;
	}

	public long getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(long gasLimit) {
		this.gasLimit = gasLimit;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
}
