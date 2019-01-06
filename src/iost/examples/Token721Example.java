package iost.examples;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import blockchain.Config;
import blockchain.TransactionHandler;
import crypto.AddressFormatException;
import crypto.iost.ActionObject;
import iost.Account;
import iost.IOST;
import iost.json.Token721Data;
import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

public class Token721Example extends AccountExample {

	public void create721Token(IOST iost, String tokenSym)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		Account account = getTestAdminAccount();
		TransactionObject tx = iost.callABI("iost", "create", tokenSym, "admin", 21000000);
		tx = account.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());

	}

	/*
	 * issue Token
	 */
	public void issue721Token(IOST iost, String tokenSym, ArrayList<Account> accountList)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		Gson gson = new Gson();
		Account account = getTestAdminAccount();
		// issue token
		Token721Data data = new Token721Data("pikaqiu", "300");
		String dataStr = gson.toJson(data);
		Iterator<Account> iterator = accountList.iterator();
		TransactionObject tx = null;
		while (iterator.hasNext()) {
			Account accountNew = iterator.next();
			if (tx == null) {
				tx = iost.callABI("iost", "issue", tokenSym, accountNew.getId(), dataStr);

			} else {
				ActionObject action = new ActionObject("issue", "iost", tokenSym, accountNew.getId(), dataStr);
				tx.setAction(action);
			}
		}
		tx = account.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());

	}

	// transfer token
	public void transfer721Token(IOST iost, String tokenSym, Account from, Account to)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		TransactionObject tx = iost.callABI("iost", "transfer", tokenSym, from.getId(), to.getId(), "0");
		tx = from.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 9);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());
	}

	// balance of token
	public void balanceOf721Token(IOST iost, String tokenSym, Account owner, Account newBalanceOwner)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		TransactionObject tx = iost.callABI("iost", "balanceOf", tokenSym, newBalanceOwner.getId());
		tx = owner.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());

	}

	// owner of token
	public void ownerOf721Token(IOST iost, String tokenSym)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		Account account = getTestAdminAccount();
		TransactionObject tx = iost.callABI("iost", "ownerOf", tokenSym, "0");
		tx = account.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());
	}

	// owner of token by index
	public void ownerOf721TokenByIndex(IOST iost, String tokenSym, Account account, int index)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		TransactionObject tx = iost.callABI("iost", "tokenOfOwnerByIndex", tokenSym, account.getId(),
				String.valueOf(index));
		tx = account.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());
	}

	// owner of token
	public void tokenMetadata721Token(IOST iost, String tokenSym)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			AddressFormatException, InvalidKeySpecException, IOException, TimeoutException {
		Account account = getTestAdminAccount();
		TransactionObject tx = iost.callABI("iost", "tokenMetadata", tokenSym, "0");
		tx = account.signTx(tx);
		TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 100, 3);
		TxReceipt receipt = transactionHandler.sendTx();
		System.out.println(receipt.getMessage());
		System.out.println(receipt.getTxHash());
	}

	public void testToken721() {
		String tokenSym = "Sb500034AAAAA";
		try {
			HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
			Config config = new Config();
			IOST iost = new IOST(config, provider);

			// ArrayList<Account> accountList = getAccountList(iost, 3);

			// Populate 3 accounts fro String[] id String Privatekey base58 encoded

			/*
			 * 
			 * u731281212 ***
			 * 36DGMPX1zGFqFwhQ9M52MGzwSZyVE52S4NaB6tAT8fepRFhZvb6Xm1CGnUPe7gCsazTyBtqBfmwogjNoHhuBGFAC
			 * u731281211 ***
			 * 5DEjgNZTzQuiiohcaCGd3Eew8ggYQqfc5QF6qY1aGxNBU1qd94whApSzTYwkqKqJqhKtGvue9cERcsJAHzzmJGtB
			 * u731281210 ***
			 * 2zdfuRBoEhzV8z1817ZTqSBSGFmiAFfybCPC9oXGd72ZkrHZbUhfvZTZn2SLS2cYg6iT2bqSPZcaSPMMskRt6mJH
			 */
			String[] ids = { "u731281210", "u731281211", "u731281212" };
			String[] pkeys = {
					"36DGMPX1zGFqFwhQ9M52MGzwSZyVE52S4NaB6tAT8fepRFhZvb6Xm1CGnUPe7gCsazTyBtqBfmwogjNoHhuBGFAC",
					"5DEjgNZTzQuiiohcaCGd3Eew8ggYQqfc5QF6qY1aGxNBU1qd94whApSzTYwkqKqJqhKtGvue9cERcsJAHzzmJGtB",
					"2zdfuRBoEhzV8z1817ZTqSBSGFmiAFfybCPC9oXGd72ZkrHZbUhfvZTZn2SLS2cYg6iT2bqSPZcaSPMMskRt6mJH" };
			ArrayList<Account> accountList = populateAccountList(ids, pkeys);

			Account account = getTestAdminAccount();
			// create token721
			create721Token(iost, tokenSym);

			// issue token
			issue721Token(iost, tokenSym, accountList);

			// transfer token
			transfer721Token(iost, tokenSym, account, accountList.get(1));

			// Balance of token
			balanceOf721Token(iost, tokenSym, account, accountList.get(0));

			// Owner of token
			ownerOf721Token(iost, tokenSym);

			// Owner of token by index
			ownerOf721TokenByIndex(iost, tokenSym, account, 0);

			// token metadata

			tokenMetadata721Token(iost, tokenSym);

			System.out.println("Test Finished");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
