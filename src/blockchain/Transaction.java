package blockchain;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import io.swagger.client.model.RpcpbTransactionResponseStatus;
import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

public class Transaction {

	private HTTPProvider provider;

	/**
     * new Transaction with Tx
	 * @param provider provider used by this transaction holder
	 * @param tx Tx
	 */
	public Transaction(HTTPProvider provider, TransactionObject tx) {
		this.provider = provider;
		this.tx = tx;
	}

	private TransactionObject tx;

    /**
     * new Transaction without default tx
     * @param provider provider
     */
	public Transaction(HTTPProvider provider) {
		this.provider = provider;
	}

	class Hash {
	    String hash;
    }

	/**
	 * Send a tx
     *
	 * @param tx tx tobe sent
	 * @throws IOException throw while send failed
     * @return tx hash
	 */
	public String sendTx(TransactionObject tx) throws IOException {
	    Gson gson = new Gson();
		String api = "sendTx";
		String query = gson.toJson(tx);
		String jsonHash = this.provider.sendPost(api, query);
		Hash hash = gson.fromJson(jsonHash, Hash.class);
		return hash.hash;
	}


	
	/**
	 * send default tx
     *
	 * @throws IOException -
	 * @return - tx hash
	 */
	public String sendTx() throws IOException {
		return this.sendTx(this.tx);
	}

	/**
	 * find tx by tx hash
	 * 
	 * @param hash - tx hash
	 * @throws IOException while net error
	 * @returns tx in json
	 */
	public String getTxByHash(String hash) throws IOException { // todo return a transaction object
		String api = "getTxByHash/" + hash;
		return this.provider.sendGet(api);
	}

	/**
	 * 通过receipt哈希查询交易结果
	 * 
	 * @param {string}hash - base58编�?的hash
	 * @throws IOException
	 * @returns {promise}
	 */
	public String getTxReceiptByHash(String hash) throws IOException {
		String api = "getTxReceiptByHash/" + hash;
		return this.provider.sendGet(api);
	}

	/**
	 * 通过交易哈希查询交易结果
	 * 
	 * @param {string}txHash - base58编�?的hash
	 * @throws IOException
	 * @returns {promise}
	 */
	public String getTxReceiptByTxHash(String txHash) throws IOException {
		String api = "getTxReceiptByTxHash/" + txHash;
		return this.provider.sendGet(api);
	}

    public TxReceipt Polling(String hash, long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i ++) {
            try {
                resStr = this.getTxReceiptByTxHash(hash);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return TxReceipt.getTxReceipt(resStr);
        } else {
            throw new TimeoutException();
        }
    }

}
