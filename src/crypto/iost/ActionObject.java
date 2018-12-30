package crypto.iost;

import com.google.gson.Gson;

public class ActionObject {

    private String contract, action_name;
    private String data;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getActionName() {
        return action_name;
    }

    public void setActionName(String actionName) {
        this.action_name = actionName;
    }

    public String getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = getJson(data);
    }

    public String getJson(Object[] data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    /**
     * @param action_name -
     * @param contract   -
     * @param data       -
     */
    public ActionObject(String actionName, String contract, Object... data) {
        this.action_name = actionName;
        this.contract = contract;
        this.data = getJson(data);
    }
}
