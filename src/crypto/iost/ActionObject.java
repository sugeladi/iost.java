package crypto.iost;

import com.google.gson.Gson;

public class ActionObject {

	private String contract, actionName;
	private String data;

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = getJson(data);
	}

	 public String getJson(String[] data) {
	    	Gson gson = new Gson();
	    	return gson.toJson(data);
	    }
	/**
	 * @param actionName
	 * @param contract
	 * @param data2
	 */
	public ActionObject(String actionName, String contract, String[] data) {
		this.actionName = actionName;
		this.contract = contract;
		this.data = getJson(data);
	}
}
