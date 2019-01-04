package crypto.iost;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import org.bouncycastle.util.encoders.Hex;
import org.omg.CORBA.DynAnyPackage.Invalid;

import crypto.Base58;
import crypto.Codec;
import crypto.Crc32;
import crypto.Ed25519;
import crypto.IostAlgo;
import crypto.Secp256k1;

public class KeyPair {
	private short algType;
	private byte[] publicKey;

	public byte[] getPublicKey() {
		return publicKey;
	}

	private byte[] privateKey;

	public byte[] getPrivateKey() {
		System.out.println(Hex.toHexString(privateKey));
		return privateKey;
	}

	private IostAlgo iostAlgo;

	public IostAlgo getIostAlgo() {
		return iostAlgo;
	}

	public void setIostAlgo(IostAlgo iostAlgo) {
		this.iostAlgo = iostAlgo;
	}

	private String id;

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
	public KeyPair(short algType) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException,
			InvalidAlgorithmParameterException {
		this.algType = algType;

		if (this.algType == Ed25519.ALGORITHMNUM) {
			iostAlgo = new Ed25519();
			Map<String, byte[]> map = getNewKeyPair();
			privateKey = map.get(IostAlgo.PrivateKey);
			publicKey = map.get(IostAlgo.PublicKey);
		} else if (this.algType == Secp256k1.ALGORITHMNUM) {
			iostAlgo = new Secp256k1();
			Map<String, byte[]> map = getNewKeyPair();
			setKeysFromRawPrivate(map.get(IostAlgo.PrivateKey));
		} else {
			throw new NoSuchAlgorithmException("Invalid Algorithim");
		}

		setId(getId(publicKey));
	}

	/**
	 * KeyPair类， 代表一个公私钥对
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 * 
	 * @constructor
	 * @Param {Byte[]}priKeyBytes - 私钥，可以通过bs58包解析base58字符串获得。
	 * @Param {short}algType - 秘钥算法，1 = Secp256k1; 2 = Ed25519
	 */
	public KeyPair(byte[] privateKey, short algType)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		this.privateKey = privateKey;
		this.algType = algType;
		if (this.algType == Ed25519.ALGORITHMNUM) {
			iostAlgo = new Ed25519();
			this.publicKey = iostAlgo.getPublicKeyFromPrivateKey(privateKey);
		} else if (this.algType == Secp256k1.ALGORITHMNUM) {
			iostAlgo = new Secp256k1();
			setKeysFromRawPrivate(privateKey);
		} else {
			throw new NoSuchAlgorithmException("Invalid Algorithim");
		}
		this.setId(getId(publicKey));
	}

	private void setKeysFromRawPrivate(byte[] pk)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		this.publicKey = iostAlgo.getPublicKeyFromPrivateKey(pk);
		this.privateKey = iostAlgo.getPrivateKey();
	}

	public String getId(byte[] data) {
		byte[] data3 = data;
		Crc32 crc = new Crc32();
		int checksumValue = crc.crcDirect(data3);
		String hex = Integer.toHexString(checksumValue);
		while (hex.length() < 8) {
			hex = "0" + hex;
		}
		byte[] crc32 = Hex.decode(hex);
		for(int i=0; i<crc32.length/2; i++){ 
			byte temp = crc32[i]; 
			crc32[i] = crc32[crc32.length -i -1]; 
			crc32[crc32.length -i -1] = temp; 
			}

		byte[] data2 = Arrays.copyOf(data, data.length + crc32.length);
		System.arraycopy(crc32, 0, data2, data.length, crc32.length);
		return "IOST" + Base58.encode(data2);
	}

// 2039514538
	/**
	 * 使用随机生成的私钥新建一个KeyPair
	 * 
	 * @param {short}algType - 秘钥算法，1 = Secp256k1; 2 = Ed25519
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @returns {KeyPair} - 生成的公私钥对
	 */
	public Map<String, byte[]> getNewKeyPair()
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		return iostAlgo.getNewKeyPair();
	}

	/**
	 * 返回私钥的base58编码字符串
	 * 
	 * @returns {string}
	 */
	public String getB58SecKey() {
		return Base58.encode(this.privateKey);
	}

	/**
	 * 返回公钥的base58编码字符串
	 * 
	 * @returns {string}
	 */
	public String B58PubKey() {
		return Base58.encode(this.publicKey);
	}

	public String B64PubKey() {
		return Base64.getEncoder().encodeToString(publicKey);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param data
	 * @returns Signature
	 * @exception Invalid Key
	 */
	public byte[] getSignature(byte[] data)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		String hex = Hex.toHexString(iostAlgo.getSignature(data, privateKey));
		hex = Base64.getEncoder().encodeToString(hex.getBytes());
		BigInteger sig = new BigInteger(hex, 16);
		return sig.toByteArray();
	}

	/**
	 * @param data
	 * @returns Signature
	 * @exception Invalid Key
	 */
	public String getSignatureAsString(byte[] data)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		return Base64.getEncoder().encodeToString(iostAlgo.getSignature(data, privateKey));

	}

	/**
	 * @param data
	 * @returns Signature
	 * @exception Invalid Key
	 */
	public byte[] getSignatureAsBytes(byte[] data)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		Codec c = new Codec();
		c.pushInt64(algType);
		c.pushBytes(iostAlgo.getSignature(data, privateKey), true);
		c.pushBytes(publicKey, true);
		return c.getBytes();
	}
}