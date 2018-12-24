package blockchain;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

public class Transaction {

	/**
	 *
	 * @Stringructor
	 * @param {RPC}rpc -
	 */
	private HTTPProvider _provider;

	/**
	 * @param _provider
	 * @param tx
	 */
	public Transaction(HTTPProvider _provider, TransactionObject tx) {
		this._provider = _provider;
		this.tx = tx;
	}

	private TransactionObject tx;

	public Transaction(HTTPProvider provider) {
		this._provider = provider;
	}

	/**
	 * �?��?交易
	 * 
	 * @param {Tx}tx
	 * @throws IOException
	 * @returns {response}
	 */
	public String sendTx(TransactionObject tx) throws IOException {
		String api = "sendTx";
		String query = new Gson().toJson(tx);
		return this._provider.sendPost(api, query);
	}
	
	/**
	 * �?��?交易
	 * 
	 * @param {Tx}tx
	 * @throws IOException
	 * @returns {response}
	 */
	public String sendTx() throws IOException {
		String api = "sendTx";
		String query = new Gson().toJson(tx);
		return this._provider.sendPost(api, query);
	}

	/**
	 * 通过交易哈希查询交易
	 * 
	 * @param {string}hash - base58编�?的hash
	 * @throws IOException
	 * @returns {promise}
	 */
	public String getTxByHash(String hash) throws IOException {
		String api = "getTxByHash/" + hash;
		return this._provider.sendGet(api);
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
		return this._provider.sendGet(api);
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
		return this._provider.sendGet(api);
	}

    public TxReceipt Polling(String hash, long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i ++) {
            try {
                resStr = this.getTxReceiptByTxHash(hash);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (!resStr.equals("")) {
            return TxReceipt.getTxReceipt(resStr);
        } else {
            throw new TimeoutException();
        }
    }

}
