package iost.json;

public class Block {

	private String hash;
	private long version;
	private String parent_hash;
	private String tx_merkle_hash;
	private String tx_receipt_merkle_hash;
	private long number;
	private String witness;
	private long time;
	private double gas_usage;
	private long tx_count;
	private Info info;
	private TransactionObject[] transactions;
	
	/**
	 * @return the info
	 */
	public Info getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(Info info) {
		this.info = info;
	}
	/**
	 * @return the transactions
	 */
	public TransactionObject[] getTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(TransactionObject[] transactions) {
		this.transactions = transactions;
	}
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
	}
	/**
	 * @return the parent_hash
	 */
	public String getParentHash() {
		return parent_hash;
	}
	/**
	 * @param parent_hash the parent_hash to set
	 */
	public void setParentHash(String parentHash) {
		this.parent_hash = parentHash;
	}
	/**
	 * @return the tx_merkle_hash
	 */
	public String getTxMerkleHash() {
		return tx_merkle_hash;
	}
	/**
	 * @param tx_merkle_hash the tx_merkle_hash to set
	 */
	public void setTxMerkleHash(String txMerkleHash) {
		this.tx_merkle_hash = txMerkleHash;
	}
	/**
	 * @return the tx_receipt_merkle_hash
	 */
	public String getTxReceiptMerkleHash() {
		return tx_receipt_merkle_hash;
	}
	/**
	 * @param tx_receipt_merkle_hash the tx_receipt_merkle_hash to set
	 */
	public void setTxReceiptMerkleHash(String txReceiptMerkleHash) {
		this.tx_receipt_merkle_hash = txReceiptMerkleHash;
	}
	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}
	/**
	 * @return the witness
	 */
	public String getWitness() {
		return witness;
	}
	/**
	 * @param witness the witness to set
	 */
	public void setWitness(String witness) {
		this.witness = witness;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the gas_usage
	 */
	public double getGasUsage() {
		return gas_usage;
	}
	/**
	 * @param gas_usage the gas_usage to set
	 */
	public void setGasUsage(double gasUsage) {
		this.gas_usage = gasUsage;
	}
	/**
	 * @return the tx_count
	 */
	public long getTxCount() {
		return tx_count;
	}
	/**
	 * @param tx_count the tx_count to set
	 */
	public void setTxCount(long txCount) {
		this.tx_count = txCount;
	}
	
}
