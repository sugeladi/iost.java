package iost.json;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TxReceipt {
    @SerializedName("status_code")
    public String statusCode;
    public String message;

    // todo decode every fields of TxReceipt

    public static TxReceipt getTxReceipt(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TxReceipt.class);
    }


}
