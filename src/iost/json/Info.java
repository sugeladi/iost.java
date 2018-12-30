package iost.json;

public class Info {

	private int mode;
	private int thread;
	private int[] batch_index;
	
	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	/**
	 * @return the thread
	 */
	public int getThread() {
		return thread;
	}
	/**
	 * @param thread the thread to set
	 */
	public void setThread(int thread) {
		this.thread = thread;
	}
	/**
	 * @return the batch_index
	 */
	public int[] getBatchIndex() {
		return batch_index;
	}
	/**
	 * @param batch_index the batch_index to set
	 */
	public void setBatchIndex(int[] batchIndex) {
		this.batch_index = batchIndex;
	}
}
