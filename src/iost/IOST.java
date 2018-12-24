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
import crypto.iost.KeyPair;
import iost.json.TransactionObject;
import provider.HTTPProvider;

public class IOST {

	private Config config;
	private HTTPProvider provider;
	private String publisher;
	private KeyPair key;

	public IOST(HTTPProvider provider) {
		this.setConfig(new Config());
		this.provider = provider;
	}
	
	public IOST(long gasRatio, long gasLimit, long delay, HTTPProvider provider) {
		super();
		config = new Config(gasRatio, gasLimit, delay);
		this.provider = provider;
	}

	/**
	 * IOST开发工具，可以帮忙发交易
	 * 
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
	 * 
	 * @param {string}contract - 智能合约ID或者域名
	 * @param {string}abi - 智能合约ABI
	 * @param {Array}args - 智能合约参数数组
	 * @throws SignatureException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @returns {Tx}
	 */
	public Transaction callABI(String contract, String abi, String[] data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		TransactionObject tx = new TransactionObject(this.config.getGasRatio(), this.getConfig().getGasLimit(),
				this.config.getDelay());
		ActionObject action = new ActionObject(abi, contract, data);
		tx.setAction(action);
		tx.setTime(90, 0);
		tx.addPublishSign(publisher, key);
		return new Transaction(provider, tx);
	}
	
	 /**
     * 设置IOST的交易发布者
     * @param {string}creator - 交易创建者的用户名
     * @param {KeyPair}kp - 交易创建者的公私钥对
     */
    public void setPublisher(String publisher, KeyPair kp) {
        this.publisher = publisher;
        this.key = kp;
    }

	/**
	 * 转账
	 * 
	 * @param {string}token - token名
	 * @param {string}to - 收款人
	 * @param {string}amount - 金额
	 * @param {string}memo - 转账备注
	 * @throws SignatureException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @returns {Tx}
	 */
	public Transaction transfer(String token, String to, String amount, String memo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		String[] data = { token, this.publisher, to, amount, memo };
		return this.callABI("token.iost", "transfer", data);
	}

	/**
	 * 新建账号
	 * 
	 * @param {string}name - 用户名
	 * @param {string}ownerkey - 用户的owner key
	 * @param {string}activekey - 用户的active key
	 * @param {number}initialRAM - 用户初始RAM
	 * @param {number}initialGasPledge - 用户初始IOST质押
	 * @throws SignatureException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @returns {Tx}
	 */
	public Transaction newAccount(String name, String ownerkey, String activekey, long initialRAM,
			long initialGasPledge) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
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
		tx.setTime(90, 0);
		tx.addPublishSign(publisher, key);
		return new Transaction(provider, tx);
	}

	/**
	 * KeyPair类， 代表一个公私钥对
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 * 
	 * @constructor
	 * @Param {Byte[]}privateKey Base58 encoded - 私钥，可以通过bs58包解析base58字符串获得。
	 * @Param {short}algType - 秘钥算法，1 = Secp256k1; 2 = Ed25519
	 */
	public KeyPair getKeyPair(String privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		byte[] seckey = Base58.decode(privateKey);		
		return new KeyPair(seckey, Ed25519.ALGORITHMNUM);		
	}
	
	
	/**
	 * KeyPair类， 代表一个公私钥对 Will create new keys
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 * 
	 * @constructor
	 * @Param {number}algType - 秘钥算法，1 = Secp256k1; 2 = Ed25519
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
