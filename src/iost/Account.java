package iost;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import crypto.iost.KeyPair;
import iost.json.TransactionObject;

public class Account {
	private Map<String, KeyPair> keyPairMap;
	/**
	 * @param keyPairMap
	 * @param id
	 */
	public Account(String id) {
		super();
		this.id = id;
		keyId = new HashMap<String, String>();
		keyPairMap = new HashMap<String, KeyPair>();
	}

	private Map<String, String> keyId;
	private String id;

	
	public String getId() {
		return id;
	}

	public KeyPair getKeyPair(String permission) {
		return keyPairMap.get(permission);
	}
	public Map<String, KeyPair> getKeyPairMap() {
		return keyPairMap;
	}

	public void addKeyPair(KeyPair keyPair, String permission) {
		if (permission == "") {
			permission = this.keyId.get(keyPair.getId());
		}
		this.keyPairMap.put(permission, keyPair);
	}

	public TransactionObject sign(TransactionObject t, String permission)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		t.addSign(this.keyPairMap.get(permission));
		return t;
	}

	public TransactionObject signTx(TransactionObject t)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		t.addPublishSign(this.id, keyPairMap.get("active"));
		return t;
	}

}
