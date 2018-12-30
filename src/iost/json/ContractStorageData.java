package iost.json;

import com.google.gson.Gson;

public class ContractStorageData {
	
	private ContractStorage data;
	
	public static ContractStorageData getContractStorageData(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, ContractStorageData.class);
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
