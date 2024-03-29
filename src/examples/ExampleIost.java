package examples;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeoutException;

import blockchain.Blockchain;
import blockchain.Config;
import blockchain.TransactionHandler;
import crypto.AddressFormatException;
import crypto.Base58;
import crypto.Ed25519;
import crypto.iost.KeyPair;
import iost.Account;
import iost.IOST;
import iost.json.BlockFromApi;
import iost.json.ChainInfo;
import iost.json.Contract;
import iost.json.ContractStorageData;
import iost.json.GasRatio;
import iost.json.NodeInfo;
import iost.json.RamInfo;
import iost.json.TokenBalance;
import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

class ExampleIost {

	public static void main(String args[]) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			NoSuchProviderException, InvalidKeySpecException, UnsupportedEncodingException {
		ExampleIost iost = new ExampleIost();
		iost.blockchain();
		iost.transfer();
		iost.newAccount();
		ContractExample cExample = new ContractExample();
		cExample.testContract();
		Token721Example tExample = new Token721Example();
		tExample.testToken721();
		System.exit(0);
	}

	/*
	 * Test Blockchain
	 */
	public void blockchain() {
		HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		Blockchain blockchain = new Blockchain(provider);
		try {

			NodeInfo info = blockchain.getNodeInfoObject(500, 2);
			System.out.println(info.getGitHash());
			System.out.println(info.getNetwork().getId());
			ChainInfo chain = blockchain.getChainInfoObject(500, 2);
			System.out.println(chain.getLibBlockHash());
			System.out.println(chain.getHeadBlockHash());
			GasRatio gasR = blockchain.getGasRatioObject(500, 3);
			System.out.println(gasR.getLowestGasRatio());
			System.out.println(gasR.getMedianGasRatio());

			RamInfo ramInfo = blockchain.getRamInfoObject(500, 3);
			System.out.println(ramInfo.getAvailableRam());
			System.out.println(ramInfo.getSellPrice());

			BlockFromApi block = blockchain.getBlockByNumber("1", "true", 500, 3);
			System.out.println(block.getBlock().getTxCount());
			System.out.println(block.getBlock().getNumber());
			System.out.println(block.getBlock().getHash());

			iost.json.Account account = blockchain.getAccount("admin", "true", 500, 3);
			System.out.println(account.getBalance());
			System.out.println(account.getRamInfo().getTotalRam());

			TokenBalance tokenBalance = blockchain.getBalance("admin", "iost", "true", 500, 3);
			System.out.println(tokenBalance.getBalance());
			Contract contract = blockchain.getContract("base.iost", "true", 500, 3);
			System.out.println(contract.getLanguage());
			System.out.println(contract.getId());

			ContractStorageData contractStorage = blockchain.getContractStorageData("vote_producer.iost",
					"producer00001", "producerTable", true, 300, 3);
			if (contractStorage.getError() == null) {
				System.out.println(contractStorage.getData().getPubkey());
				System.out.println(contractStorage.getData().getRegisterFee());
			} else {
				System.out.println("Error Data: " + contractStorage.getError());
			}
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void transfer() {

		try {

			HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
			Config config = new Config();
			IOST iost = new IOST(config, provider);

			// init admin account
			Account account = new Account("admin");
			KeyPair kp = new KeyPair(
					Base58.decode(
							"2yquS3ySrGWPEKywCPzX4RTJugqRh7kJSo5aehsLYPEWkUxBWA39oMrZ7ZxuM4fgyXYs2cPwh5n8aNNpH5x2VyK1"),
					Ed25519.ALGORITHMNUM);
			account.addKeyPair(kp, "owner");
			account.addKeyPair(kp, "active");

			// send a call
			TransactionObject transactionObj = iost.transfer("iost", "admin", "admin", "10.000", "");
			transactionObj = account.signTx(transactionObj);

			// send tx and using transaction handler
			TransactionHandler transactionHandler = new TransactionHandler(provider, transactionObj, 30, 3);
			TxReceipt receipt = transactionHandler.sendTx();
			System.out.println(receipt.getMessage());

		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	/**
	 * Create New Account
	 */
	public void newAccount() {
		try {
			HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
			Config config = new Config();
			IOST iost = new IOST(config, provider);

			// init admin account
			Account account = new Account("admin");
			KeyPair kp = new KeyPair(
					Base58.decode(
							"2yquS3ySrGWPEKywCPzX4RTJugqRh7kJSo5aehsLYPEWkUxBWA39oMrZ7ZxuM4fgyXYs2cPwh5n8aNNpH5x2VyK1"),
					Ed25519.ALGORITHMNUM);
			account.addKeyPair(kp, "owner");
			account.addKeyPair(kp, "active");

			// new keypair and create account
			KeyPair newKP = new KeyPair(Ed25519.ALGORITHMNUM);

			Account testAccount = new Account("test1c");
			testAccount.addKeyPair(newKP, "owner");
			testAccount.addKeyPair(newKP, "active");
			// send a call

			TransactionObject transactionObject = iost.newAccount(testAccount.getId(), "admin", newKP.getId(),
					newKP.getId(), 1024, 10);
			transactionObject = account.signTx(transactionObject);

			// send tx and handler result
			TransactionHandler transactionHandler = new TransactionHandler(provider, transactionObject, 100, 3);
			TxReceipt receipt = transactionHandler.sendTx();
			System.out.println(receipt.getMessage());
			System.out.println(receipt.getGasUsage());

			Blockchain blockchain = new Blockchain(provider);
			iost.json.Account jaccount = blockchain.getAccount(testAccount.getId(), "true", 500, 3);
			System.out.println(jaccount.getBalance());
			System.out.println(jaccount.getRamInfo().getAvailable());

		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}