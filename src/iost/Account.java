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
	private Map<String, KeyPair> keyPairMap = new HashMap<String, KeyPair>();;
	/**
	 * @param keyPairMap
	 * @param id
	 */
	public Account(String id, KeyPair keyPair) {
		super();
		this.id = id;
		setKeyPairMap("active", keyPair);
	}

	private Map<String, String> keyId = new HashMap<String, String>();;
	private String id;

	public Account(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public KeyPair getKeyPair(String permission) {
		return keyPairMap.get(permission);
	}
	public Map<String, KeyPair> getKeyPairMap() {
		return keyPairMap;
	}

	public void setKeyPairMap(String permission, KeyPair keyPair) {
		if (permission == "") {
			permission = this.keyId.get(keyPair.getId());
		}
		this.keyPairMap.put(permission, keyPair);
	}

	public TransactionObject SignTx(TransactionObject t, String permission)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		t.addSign(this.keyPairMap.get(permission));
		return t;
	}

	public TransactionObject PublishTx(TransactionObject t, String permission)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		t.addPublishSign(this.id, keyPairMap.get(permission));
		return t;
	}

}
