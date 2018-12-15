package crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface IostAlgo {
	public String PublicKey = "PublicKey";
	public String PrivateKey = "PrivateKey";
	
	public byte[] getPublicKey();
	public byte[] getPrivateKey();
	public String getAlgName();
	public Map<String, byte[]> getNewKeyPair()
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException;

	public byte[] getPublicKeyFromPrivateKey(byte[] pk)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException;

	public byte[] getSignature(byte[] data, byte[] privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException;

	public boolean verifySignature(byte[] data, byte[] sign)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException;
}
