package crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import crypto.TweetNaCl.InvalidSignatureException;

/*
 * EdDSA Operation	ref10 function	Java function
Generate keypair	crypto_sign_keypair	EdDSAPrivateKeySpec constructor
Sign message	crypto_sign	EdDSAEngine.engineSign
Verify signature	crypto_sign_open	EdDSAEngine.engineVerify
EdDSA point arithmetic	ref10 function	Java function
R = b * B	ge_scalarmult_base	GroupElement.scalarMultiply
R = a*A + b*B	ge_double_scalarmult_vartime	GroupElement.doubleScalarMultiplyVariableTime
R = 2 * P	ge_p2_dbl	GroupElement.dbl
R = P + Q	ge_madd, ge_add	GroupElement.madd, GroupElement.add
R = P - Q	ge_msub, ge_sub	GroupElement.msub, GroupElement.sub
 */


public class Ed25519 implements IostAlgo{
	public static final String ALGORITHM = "ed25519";
	public static final Short ALGORITHMNUM = 2;
	
	byte[] publicKey;
	 byte[] privateKey;
	
	public byte[] getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	public byte[] getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

	private native byte[] ExpandPrivateKeyN(byte[] privateKey);

	private native byte[] SignN(byte[] message, byte[] privateKey);

	private native int VerifyN(byte[] message, byte[] signature, byte[] publicKey);

	public byte[] ExpandPrivateKey(byte[] privateKey) throws InvalidKeySpecException {
		if ((privateKey.length != 32) && (privateKey.length != 64))
			throw new InvalidKeySpecException("Invalid privateKey length, 32 bytes please");
		if (privateKey.length == 64) { // already expanded.
			return privateKey;
		}
		return ExpandPrivateKeyN(privateKey);
	}

	public byte[] PublicKeyFromPrivateKey(byte[] privateKey) throws InvalidKeySpecException {
		if ((privateKey.length != 32) && (privateKey.length != 64))
			throw new InvalidKeySpecException("Invalid privateKey length, 32 or 64 bytes please");
		if (privateKey.length == 32) {
			privateKey = ExpandPrivateKey(privateKey);
		}
		return Arrays.copyOfRange(privateKey, privateKey.length/2, privateKey.length);
	}

	public byte[] Sign(byte[] message, byte[] privateKey) throws InvalidKeySpecException {
		if ((privateKey.length != 32) && (privateKey.length != 64))
			throw new InvalidKeySpecException("Invalid privateKey length, must be 32 or 64 bytes");
		if (privateKey.length == 32) {
			privateKey = ExpandPrivateKey(privateKey);
		}
		return SignN(message, privateKey);
	}

	public int Verify(byte[] message, byte[] signature, byte[] publicKey) throws InvalidKeySpecException {
		if (publicKey.length != 32)
			throw new InvalidKeySpecException("Invalid publicKey length, must be 32 bytes");
		if (signature.length != 64)
			return -1;
		return VerifyN(message, signature, publicKey);
	}

	private MessageDigest SHA512_Init() {
		try {
			return MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void SHA512_Update(MessageDigest md, byte[] data) {
		md.update(data);
	}

	private byte[] SHA512_Final(MessageDigest md) {
		return md.digest();
	}

	@Override
	public Map<String, byte[]> getNewKeyPair()
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		publicKey = new byte[TweetNaCl.SIGN_PUBLIC_KEY_BYTES];
		privateKey = new byte[TweetNaCl.SIGN_SECRET_KEY_BYTES];
		TweetNaCl.crypto_sign_keypair(publicKey, privateKey, false);
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		map.put(PublicKey, publicKey);
		map.put(PrivateKey, privateKey);
		return map;
	}

	@Override
	public byte[] getPublicKeyFromPrivateKey(byte[] privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		if ((privateKey.length != 32) && (privateKey.length != 64))
			throw new InvalidKeySpecException("Invalid privateKey length, 32 or 64 bytes please");
		if (privateKey.length == 32) {
			privateKey = ExpandPrivateKey(privateKey);
		}
		this.privateKey = privateKey;
		this.publicKey = Arrays.copyOfRange(privateKey, 32, 64);
		return this.publicKey;
	}

	@Override
	public byte[] getSignature(byte[] data, byte[] privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException{
		return TweetNaCl.crypto_sign(data, privateKey);
	}

	@Override
	public boolean verifySignature(byte[] data, byte[] signed)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException {
		try {
		TweetNaCl.crypto_sign_open(signed, publicKey);
        }catch(InvalidSignatureException ise) {return false;}return true;
	}

	@Override
	public String getAlgName() {
		return Ed25519.ALGORITHM;
	}	
}