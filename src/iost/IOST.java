package iost;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import blockchain.Config;
import crypto.iost.ActionObject;
import iost.json.TransactionObject;
import provider.HTTPProvider;

public class IOST {

	private Config config;
	private HTTPProvider provider;

	/**
	 * @return the provider
	 */
	public HTTPProvider getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(HTTPProvider provider) {
		this.provider = provider;
	}


	/**
	 * constructor with config settings
	 * @param defaultLimit is Amount Limit
	 *
	 */
	public IOST(long gasRatio, long gasLimit, long delay, long expiration, String defaultLimit, HTTPProvider provider) {
		config = new Config(gasRatio, gasLimit, delay, expiration, defaultLimit);
		this.provider = provider;
	}

	/**
	 * IOST开发工具，可以帮忙发交易
	 * @constructor
	 * @param {object}config - 这个iost的配置
	 * @param {HTTPProvider} - provider
	 */
	public IOST(Config config, HTTPProvider provider) {
		this.config = config;
		this.provider = provider;
	}

	/**
	    * 调用智能合约ABI
     * @param {string}contract - 智能合约ID或者域名
     * @param {string}abi - 智能合约ABI
     * @param {Array}args - 智能合约参数数组
	 * @re.lo[jiojturn a TransactionObject
	 */
	public TransactionObject callABI(String contract, String abi, Object... data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		TransactionObject t = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit());
		ActionObject action = new ActionObject(abi, contract, data);
		t.setAction(action);
		t.setTime(this.config.getExpiration(), this.getConfig().getDelay());
		t.addApprove("*", this.config.getDefaultLimit());
		return t;
	}
	


	/**
	 * 转账
	 * 
	 * @param token - token名
	 * @param to - 收款人
	 * @param amount - 金额
	 * @param memo - 转账备注
	 * @throws SignatureException -
	 * @throws NoSuchProviderException -
	 * @throws NoSuchAlgorithmException -
	 * @throws InvalidKeyException -
	 * @throws UnsupportedEncodingException -
	 * @return - 转账的transaction Object
	 */
	public TransactionObject transfer(String token,String from, String to, String amount, String memo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		TransactionObject t =this.callABI("token.iost", "transfer", token, from, to, amount, memo);
	//	t.addApprove("*", this.config.getAmountLimit());
        t.addApprove("iost", amount);
        return t;
	}

	/**
	 * 新建账号
	 * 
	 * @param name - 用户名
	 * @param ownerkey - 用户的owner key
	 * @param activekey - 用户的active key
	 * @param initialRAM - 用户初始RAM
	 * @param initialGasPledge - 用户初始IOST质押
	 * @throws SignatureException -
	 * @throws NoSuchProviderException -
	 * @throws NoSuchAlgorithmException -
	 * @throws InvalidKeyException -
	 * @throws UnsupportedEncodingException -
	 * @return {transaction Object}
	 */
	public TransactionObject newAccount(String name, String creator, String ownerkey, String activekey, long initialRAM,
			double initialGasPledge) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		TransactionObject t = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit());
		ActionObject action = new ActionObject("SignUp", "auth.iost", name, ownerkey, activekey);
		t.setAction(action);
		ActionObject action2 = new ActionObject("buy", "ram.iost", creator, name, initialRAM);
		t.setAction(action2);
		ActionObject action3 = new ActionObject("pledge", "gas.iost", creator, name, String.valueOf(initialGasPledge));
		t.setAction(action3);
		t.setTime(this.config.getExpiration(), 30);
		t.addApprove("*", this.config.getDefaultLimit());
		return t;
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
