package iost.json;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.swagger.client.model.RpcpbSendTransactionResponse;
import io.swagger.client.model.RpcpbTransactionResponse;
import io.swagger.client.model.RpcpbTxReceipt;

public class TxReceipt extends RpcpbTxReceipt {

    // todo decode every fields of TxReceipt

    public static TxReceipt getTxReceipt(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TxReceipt.class);
    }
}
