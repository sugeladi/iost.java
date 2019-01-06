package iost.examples;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import blockchain.Blockchain;
import blockchain.Config;
import blockchain.TransactionHandler;
import iost.Account;
import iost.IOST;
import iost.json.Abi;
import iost.json.Contract;
import iost.json.ContractInfo;
import iost.json.ContractObject;
import iost.json.TransactionObject;
import iost.json.TxReceipt;
import provider.HTTPProvider;

public class ContractExample extends AccountExample{

	public void testContract() {
		HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		Config config = new Config();
		IOST iost = new IOST(config, provider);

		String js = "class Contract {init(){} hello(){return 'world';} can_update(){return true;}} module.exports = Contract;";

		ContractInfo newContract = new ContractInfo();
		newContract.setLang("javascript");
		newContract.setVersion("1.0.0");
		Abi abi1 = new Abi();
		abi1.setName("hello");
		Abi abi2 = new Abi();
		abi2.setName("can_update");
		Abi[] abis = { abi1, abi2 };
		newContract.setAbi(abis);
		ContractObject cont = new ContractObject("contid", newContract);
		cont.setCode(js);
		Gson gson = new Gson();
		String helloContract = gson.toJson(cont);
		try {
			/*
			 * Create Contract
			 */

			TransactionObject tx = iost.callABI("system.iost", "SetCode", helloContract);
			Account account = getTestAdminAccount();
			tx = account.signTx(tx);
			TransactionHandler transactionHandler = new TransactionHandler(iost.getProvider(), tx, 30, 3);
			Contract contract = printTransactionRcpt(provider, transactionHandler.sendTx(tx), true);

			/*
			 * Call Contract hello function
			 */

			tx = iost.callABI(contract.getId(), "hello");
			tx = account.signTx(tx);
			transactionHandler = new TransactionHandler(iost.getProvider(), tx, 30, 3);
			printTransactionRcpt(provider, transactionHandler.sendTx(tx), false);
			System.out.println("Finished");

			/*
			 * Edit contract
			 */

			js = "class Contract {init(){} hello(data){return data;} can_update(data){return false;}} module.exports = Contract;";

			newContract = new ContractInfo();
			newContract.setLang("javascript");
			newContract.setVersion("1.0.0");
			abi1.setName("hello");
			String[] args = { "string" };
			abi1.setArgs(args);
			abi2.setArgs(args);
			abi2.setName("can_update");
			Abi[] abis2 = { abi1, abi2 };
			newContract.setAbi(abis2);
			cont = new ContractObject("contid", newContract);
			cont.setCode(js);
			helloContract = gson.toJson(cont);

			tx = iost.callABI("system.iost", "SetCode", helloContract);
			account = getTestAdminAccount();
			tx = account.signTx(tx);
			transactionHandler = new TransactionHandler(iost.getProvider(), tx, 30, 3);
			contract = printTransactionRcpt(provider, transactionHandler.sendTx(tx), true);

			/*
			 * Call Contract hello function
			 */

			tx = iost.callABI(contract.getId(), "hello","Testing Updated Contract");
			tx = account.signTx(tx);
			transactionHandler = new TransactionHandler(iost.getProvider(), tx, 30, 3);
			printTransactionRcpt(provider, transactionHandler.sendTx(tx), false);
			System.out.println("Finished");
			
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	public Contract printTransactionRcpt(HTTPProvider provider, TxReceipt receipt, boolean returnContract)
			throws TimeoutException {
		Gson gson = new Gson();
		System.out.println(receipt.getMessage());
		String[] returns = receipt.getReturns();
		if (returns.length > 0) {
			System.out.println("RCPT HASH: " + receipt.getTxHash());
			System.out.println("Contract Response: ");
			ArrayList<String> cidl = gson.fromJson(returns[0], ArrayList.class);
			System.out.println(cidl.get(0));
			if (returnContract) {
				/* Get newly create contract from blockchain */
				Blockchain blockchain = new Blockchain(provider);
				Contract contract = blockchain.getContract(cidl.get(0), "true", 30, 3);
				System.out.println(contract.getId());
				System.out.println(contract.getCode());
				return contract;
			}
		}
		return null;
	}
}
