package crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Secp256k1 implements IostAlgo {

	private PrivateKey privKey;
	private PublicKey publicKey;

	public byte[] getPublicKey() {
		return publicKey.toString().getBytes();
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	private KeyPair kp;

	public static final String ALGORITHM = "secp256k1";
	public static final short ALGORITHMNUM = 1;

	public Map<String, byte[]> getNewKeyPair()
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
		ECGenParameterSpec ecsp;
		ecsp = new ECGenParameterSpec(Secp256k1.ALGORITHM);
		kpg.initialize(ecsp);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		kpg.initialize(ecsp, random);
		kp = kpg.generateKeyPair();
		privKey = kp.getPrivate();
		publicKey = kp.getPublic();
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		map.put("PublicKey", publicKey.toString().getBytes());
		map.put("PrivateKey", privKey.toString().getBytes());
		return map;
	}

	public byte[] getPublicKeyFromPrivateKey(byte[] pk)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pk);
		KeyFactory kf = KeyFactory.getInstance("EC", "SunEC");
		publicKey = kf.generatePublic(publicKeySpec);
		privKey = kf.generatePrivate(publicKeySpec);
		return publicKey.toString().getBytes();
	}

	public byte[] getSignature(byte[] data, byte[] privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		Signature ecdsa;
		ecdsa = Signature.getInstance("SHA1withECDSA", "SunEC");
		ecdsa.initSign(privKey);
		ecdsa.update(data);
		return ecdsa.sign();
	}

	public boolean verifySignature(byte[] data, byte[] sign)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		Signature signature;
		signature = Signature.getInstance("SHA1withECDSA", "SunEC");
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(sign);
	}

	@Override
	public byte[] getPrivateKey() {
		// TODO Auto-generated method stub
		return privKey.toString().getBytes();
	}

	@Override
	public String getAlgName() {
		// TODO Auto-generated method stub
		return Secp256k1.ALGORITHM;
	}
}
