package iost.json;

import com.google.gson.Gson;

public class ContractStorageData {
	
	private ContractStorage data;
	private String error;
	
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	public static ContractStorageData getContractStorageData(String json) {
		String st = "{\"data\":\"";
		String et = "\"}";
		json = json.substring(st.length(), json.lastIndexOf(et));
		ContractStorageData data = new ContractStorageData();
		if (json.startsWith("{")) {
			 data.data = ContractStorage.getContractStorage(json);	
		}else {data.error = json;}
	 
	 
	 return data;
	}

	/**
	 * @return the data
	 */
	public ContractStorage getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ContractStorage data) {
		this.data = data;
	}

}
