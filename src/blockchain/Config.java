package blockchain;

public class Config {

	private long gasRatio, gasLimit, delay;

	public Config() {
		super();
		this.gasRatio = 1;
		this.gasLimit = 100000;
		this.delay = 0;
	}

	public Config(long gasRatio, long gasLimit, long delay) {
		super();
		this.gasRatio = gasRatio;
		this.gasLimit = gasLimit;
		this.delay = delay;
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
