package iost.json;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TxReceipt {
    @SerializedName("status_code")
    private String status_code;
    private String message;
    
    private String tx_hash;
    private double gas_usage;
    private Map<String, Integer> ram_usage;
    private String[] returns;
    private Receipt[] receipts;

   

    /**
	 * @return the status_code
	 */
	public String getStatusCode() {
		return status_code;
	}

	/**
	 * @param status_code the status_code to set
	 */
	public void setStatusCode(String statusCode) {
		this.status_code = statusCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the tx_hash
	 */
	public String getTxHash() {
		return tx_hash;
	}

	/**
	 * @param tx_hash the tx_hash to set
	 */
	public void setTxHash(String txHash) {
		this.tx_hash = txHash;
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
	 * @return the ram_usage
	 */
	public Map<String, Integer> getRamUsage() {
		return ram_usage;
	}

	/**
	 * @param ram_usage the ram_usage to set
	 */
	public void setRamUsage(Map<String, Integer> ramUsage) {
		this.ram_usage = ramUsage;
	}

	/**
	 * @return the returns
	 */
	public String[] getReturns() {
		return returns;
	}

	/**
	 * @param returns the returns to set
	 */
	public void setReturns(String[] returns) {
		this.returns = returns;
	}

	/**
	 * @return the receipts
	 */
	public Receipt[] getReceipts() {
		return receipts;
	}

	/**
	 * @param receipts the receipts to set
	 */
	public void setReceipts(Receipt[] receipts) {
		this.receipts = receipts;
	}

	public static TxReceipt getTxReceipt(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TxReceipt.class);
    }


}
