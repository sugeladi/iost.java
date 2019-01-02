import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeoutException;

import blockchain.Api;
import blockchain.Config;
import blockchain.TransactionHandler;
import crypto.Base58;
import crypto.Ed25519;
import crypto.iost.KeyPair;
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
		ExampleIost algo = new ExampleIost();
		algo.blockchain();
		algo.transfer();
		algo.newAccount();
	}

	/*
	 * Test BlockChain
	 */
	public void blockchain() {
		HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		Api api = new Api(provider);
		try {

			NodeInfo info = api.getNodeInfoObject(500, 2);
			System.out.println(info.getGitHash());
			System.out.println(info.getNetwork().getId());
			ChainInfo chain = api.getChainInfoObject(500, 2);
			System.out.println(chain.getLibBlockHash());
			System.out.println(chain.getHeadBlockHash());
			GasRatio gasR = api.getGasRatioObject(500, 3);
			System.out.println(gasR.getLowestGasRatio());
			System.out.println(gasR.getMedianGasRatio());

			RamInfo ramInfo = api.getRamInfoObject(500, 3);
			System.out.println(ramInfo.getAvailableRam());
			System.out.println(ramInfo.getSellPrice());

			BlockFromApi block = api.getBlockByNumber("1", "true", 500, 3);
			System.out.println(block.getBlock().getTxCount());
			System.out.println(block.getBlock().getNumber());
			System.out.println(block.getBlock().getHash());

			iost.json.Account account = api.getAccount("admin", "true", 500, 3);
			System.out.println(account.getBalance());
			System.out.println(account.getRamInfo().getTotalRam());

			TokenBalance tokenBalance = api.getTokenBalance("admin", "iost", "true", 500, 3);
			System.out.println(tokenBalance.getBalance());

			Contract contract = api.getContract("base.iost", "true", 500, 3);
			System.out.println(contract.getLanguage());
			System.out.println(contract.getId());

			ContractStorageData contractStorage = api.getContractStorageData("vote_producer.iost", "producer00001",
					"producerTable", true, 300, 3);
			System.out.println(contractStorage.getData().getPubkey());
			System.out.println(contractStorage.getData().getRegisterFee());
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

			
			// send a call
			TransactionObject transactionObject = iost.newAccount("test1", "admin", newKP.getId(), newKP.getId(), 1024, 10);
			transactionObject = account.signTx(transactionObject);

			// send tx and handler result
			TransactionHandler transactionHandler = new TransactionHandler(provider, transactionObject, 100, 3);
			TxReceipt receipt = transactionHandler.sendTx();
			System.out.println(receipt.getMessage());
			System.out.println(receipt.getGasUsage());
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