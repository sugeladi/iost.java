package iost;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import blockchain.Config;
import blockchain.Transaction;
import crypto.Base58;
import crypto.Ed25519;
import crypto.iost.ActionObject;
import crypto.iost.AmountLimitObject;
import crypto.iost.KeyPair;
import iost.json.TransactionObject;
import provider.HTTPProvider;

public class IOST {

	private Config config;
	private HTTPProvider provider;
	private String publisher;
	private KeyPair key;

	/**
	 * new iost with default config
	 *
	 * @param provider - network provider
	 */
	public IOST(HTTPProvider provider) {
		this.setConfig(new Config());
		this.provider = provider;
	}

	/**
	 * constructor with config settings
	 *
	 * @param gasRatio -
	 * @param gasLimit -
	 * @param delay -
	 * @param provider -
	 */
	public IOST(long gasRatio, long gasLimit, long delay, HTTPProvider provider) {
		super();
		config = new Config(gasRatio, gasLimit, delay);
		this.provider = provider;
	}

	/**
	 * constructor with config
	 * 
	 * @param config -
	 * @param provider - provider
	 */
	public IOST(Config config, HTTPProvider provider) {
		this.config = config;
		this.provider = provider;
	}

	/**
	 * 调用智能合约ABI
	 * 
	 * @param contract - 智能合约ID或者域名
	 * @param abi - 智能合约ABI
	 * @param data - 智能合约参数数组
	 * @throws SignatureException  -
	 * @throws NoSuchProviderException  -
	 * @throws NoSuchAlgorithmException  -
	 * @throws InvalidKeyException  -
	 * @throws UnsupportedEncodingException  -
	 * @return a Transaction with tx
	 */
	public Transaction callABI(String contract, String abi, String[] data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		TransactionObject tx = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit(),
				this.config.getDelay());
		ActionObject action = new ActionObject(abi, contract, data);
		tx.setAction(action);
		tx.setTime(90, 0);
		tx.setAmount_limit(new AmountLimitObject("*", "unlimited"));
		tx.addPublishSign(publisher, key);
		return new Transaction(provider, tx);
	}
	
	 /**
     * 设置IOST的交易发布者
     * @param publisher - 交易创建者的用户名
     * @param kp - 交易创建者的公私钥对
     */
    public void setPublisher(String publisher, KeyPair kp) {
        this.publisher = publisher;
        this.key = kp;
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
	 * @return - 转账的tx
	 */
	public Transaction transfer(String token, String to, String amount, String memo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		String[] data = { token, this.publisher, to, amount, memo };
		return this.callABI("token.iost", "transfer", data);
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
	 * @return {Tx}
	 */
	public Transaction newAccount(String name, String ownerkey, String activekey, long initialRAM,
			double initialGasPledge) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException{
		TransactionObject tx = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit(),
				this.config.getDelay());
		ActionObject action = new ActionObject("SignUp", "auth.iost", name, ownerkey, activekey);
		tx.setAction(action);
		ActionObject action2 = new ActionObject("buy", "ram.iost", this.publisher, name, initialRAM);
		tx.setAction(action2);
		ActionObject action3 = new ActionObject("pledge", "gas.iost", this.publisher, name, String.valueOf(initialGasPledge));
		tx.setAction(action3);
		tx.setTime(90, 0);
		tx.setAmount_limit(new AmountLimitObject("*", 1000000));
		tx.addPublishSign(publisher, key);
		return new Transaction(provider, tx);
	}

	/**
	 * KeyPair类， 代表一个公私钥对
	 * 
	 * @throws NoSuchAlgorithmException -
	 * @throws NoSuchProviderException -
	 * @throws InvalidKeySpecException -
	 * 
	 * @param privateKey Base58 encoded - 私钥，可以通过bs58包解析base58字符串获得。
	 */
	public KeyPair getKeyPair(String privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		byte[] seckey = Base58.decode(privateKey);		
		return new KeyPair(seckey, Ed25519.ALGORITHMNUM);		
	}
	
	
	/**
	 * KeyPair类， 代表一个公私钥对 Will create new keys
	 *
     * @param algonum - 秘钥算法，1 = Secp256k1; 2 = Ed25519
     *
     * @throws NoSuchAlgorithmException -
	 * @throws NoSuchProviderException -
	 * @throws InvalidKeySpecException -
	 * @throws InvalidAlgorithmParameterException -
	 * 
	 */
	public KeyPair getNewKeyPair(short algonum) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
			InvalidAlgorithmParameterException {
		return new KeyPair(algonum);
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
