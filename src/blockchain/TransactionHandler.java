package blockchain;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

public class TransactionHandler {

	private HTTPProvider provider;

	/**
     * new TransactionHandler with Tx
	 * @param provider provider used by this transaction holder
	 * @param transactionObject Tx
	 */
	public TransactionHandler(HTTPProvider provider, TransactionObject transactionObj) {
		this.provider = provider;
		this.transactionObject = transactionObj;
	}

	private TransactionObject transactionObject;

    /**
     * new TransactionHandler without default transactionObject
     * @param provider provider
     */
	public TransactionHandler(HTTPProvider provider) {
		this.provider = provider;
	}

	class Hash {
	    String hash;
    }

	/**
	 * Send a transactionObject
     *
	 * @param transactionObject transactionObject tobe sent
	 * @throws IOException throw while send failed
     * @return transactionObject hash
	 * @throws TimeoutException 
	 */
	public TxReceipt sendTx(TransactionObject tx) throws IOException, TimeoutException {
	    Gson gson = new Gson();
		String api = "sendTx";
		String query = gson.toJson(tx);
		String jsonHash = this.provider.sendPost(api, query);
		Hash hash = gson.fromJson(jsonHash, Hash.class);
		return Polling(hash.hash, provider.getIntervalInMillis(), provider.getTimes());
	}


	
	/**
	 * send default transactionObject
     *
	 * @throws IOException -
	 * @return - transactionObject hash
	 * @throws TimeoutException 
	 */
	public TxReceipt sendTx() throws IOException, TimeoutException {
		return this.sendTx(this.transactionObject);
	}

	/**
	 * find transactionObject by transactionObject hash
	 * 
	 * @param hash - transactionObject hash
	 * @throws IOException while net error
	 * @returns transactionObject in json
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
