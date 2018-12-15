package iost;

import blockchain.Config;
import crypto.iost.ActionObject;
import crypto.iost.KeyPair;
import crypto.iost.TransactionObject;
import provider.HTTPProvider;

public class IOST {

	private Config config;
	private HTTPProvider provider;
	private String publisher;
	private KeyPair key;

	public IOST(String publisher, KeyPair key) {
		this.setConfig(new Config());
		this.publisher = publisher;
		this.key = key;
	}

	/**
	 * IOST开发工具，可以帮忙发交易
	 * 
	 * @constructor
	 * @param {object}config - 这个iost的配置
	 * @param {HTTPProvider} - provider
	 */
	public IOST(Config config, HTTPProvider provider, String publisher) {
		this.config = config;
		this.provider = provider;
		this.publisher = publisher;
	}

	/**
	 * 调用智能合约ABI
	 * 
	 * @param {string}contract - 智能合约ID或者域名
	 * @param {string}abi - 智能合约ABI
	 * @param {Array}args - 智能合约参数数组
	 * @returns {Tx}
	 */
	public TransactionObject callABI(String contract, String abi, String[] data) {
		TransactionObject tx = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit(),
				this.config.getDelay());
		ActionObject action = new ActionObject(abi, contract, data);
		tx.setAction(action);
		tx.setTime(90, 0);
		return tx;
	}

	/**
	 * 转账
	 * 
	 * @param {string}token - token名
	 * @param {string}to - 收款人
	 * @param {string}amount - 金额
	 * @param {string}memo - 转账备注
	 * @returns {Tx}
	 */
	public TransactionObject transfer(String token, String to, String amount, String memo) {
		String[] data = { token, this.publisher, to, amount, memo };
		return this.callABI("iost.token", "transfer", data);
	}

	/**
	 * 新建账号
	 * 
	 * @param {string}name - 用户名
	 * @param {string}ownerkey - 用户的owner key
	 * @param {string}activekey - 用户的active key
	 * @param {number}initialRAM - 用户初始RAM
	 * @param {number}initialGasPledge - 用户初始IOST质押
	 * @returns {Tx}
	 */
	public TransactionObject newAccount(String name, String ownerkey, String activekey, long initialRAM,
			long initialGasPledge) {
		TransactionObject tx = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit(),
				this.config.getDelay());
		String[] data = { name, ownerkey, activekey };
		ActionObject action = new ActionObject("SignUp", "auth.iost", data);
		tx.setAction(action);
		String[] data2 = { this.publisher, name, initialRAM + "" };
		ActionObject action2 = new ActionObject("buy", "ram.iost", data2);
		tx.setAction(action2);
		String[] data3 = { this.publisher, name, initialGasPledge + "" };
		ActionObject action3 = new ActionObject("pledge", "gas.iost", data3);
		tx.setAction(action3);
		return tx;
	}

	/**
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(Config config) {
		this.config = config;
	}

}
