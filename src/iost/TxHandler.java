package iost;

import blockchain.Transaction;
import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class TxHandler {
    private String hash;
    private Transaction transaction;
    private TransactionObject tx;

    public TxHandler(TransactionObject tx, HTTPProvider provider) {
        this.transaction = new Transaction(provider);
        this.hash = null;
        this.tx = tx;
    }

    public String send() throws IOException {
        this.hash = this.transaction.sendTx(this.tx);
        return hash;
    }

    public TxReceipt Polling(long intervalInMillis, int times) throws IOException, TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i ++) {
            try {
                resStr = this.transaction.getTxReceiptByTxHash(this.hash);
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
