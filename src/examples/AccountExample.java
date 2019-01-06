package examples;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import blockchain.TransactionHandler;
import crypto.AddressFormatException;
import crypto.Base58;
import crypto.Ed25519;
import crypto.iost.KeyPair;
import iost.Account;
import iost.IOST;
import iost.json.TransactionObject;
import iost.json.TxReceipt;

public class AccountExample {

	public Account getTestAdminAccount()
			throws AddressFormatException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		// init admin account
		Account account = new Account("admin");
		KeyPair kp = new KeyPair(
				Base58.decode(
						"2yquS3ySrGWPEKywCPzX4RTJugqRh7kJSo5aehsLYPEWkUxBWA39oMrZ7ZxuM4fgyXYs2cPwh5n8aNNpH5x2VyK1"),
				Ed25519.ALGORITHMNUM);
		account.addKeyPair(kp, "owner");
		account.addKeyPair(kp, "active");
		return account;
	}

	public ArrayList<Account> getAccountList(IOST iost, int totalAccounts)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
			InvalidAlgorithmParameterException, InvalidKeyException, SignatureException, IOException, TimeoutException {
		Account account = getTestAdminAccount();
		ArrayList<Account> accounts = new ArrayList<Account>();
		String userPrefix = new Date().getTime() + "";
		userPrefix = "u" + userPrefix.substring(userPrefix.length() - 8);

		for (int i = 0; i < totalAccounts; i++) {
			KeyPair newKP = new KeyPair(Ed25519.ALGORITHMNUM);
			TransactionObject transactionObject = iost.newAccount(userPrefix + i, "admin", newKP.getId(), newKP.getId(),
					10241024, 10234);
			transactionObject = account.signTx(transactionObject);

			// send tx and handler result
			TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), transactionObject, 100,
					3);
			TxReceipt receipt = transactionHandler.sendTx();
			System.out.println(receipt.getMessage());
			System.out.println(receipt.getGasUsage());
			Account account2 = new Account(userPrefix + i);
			account2.addKeyPair(newKP, "owner");
			account2.addKeyPair(newKP, "active");
			accounts.add(account2);
			System.out.println(account2.getId() + " *** " + newKP.getB58SecKey());
		}
		return accounts;
	}

	public ArrayList<Account> populateAccountList(String[] ids, String[] pkeys)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
			InvalidAlgorithmParameterException, InvalidKeyException, SignatureException, IOException, TimeoutException {
		ArrayList<Account> accounts = new ArrayList<Account>();

		for (int i = 0; i < ids.length; i++) {
			KeyPair newKP = new KeyPair(Base58.decode(pkeys[i]), Ed25519.ALGORITHMNUM);
			Account account2 = new Account(ids[i]);
			account2.addKeyPair(newKP, "owner");
			account2.addKeyPair(newKP, "active");
			accounts.add(account2);
			System.out.println(account2.getId() + " *** " + newKP.getB58SecKey());
		}
		return accounts;
	}

}
