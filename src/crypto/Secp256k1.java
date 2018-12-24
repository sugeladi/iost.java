package crypto;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.encoders.Hex;

public class Secp256k1 implements IostAlgo {
	static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
	public static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
	static final ECDomainParameters CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(),
			CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
	static final BigInteger HALF_CURVE_ORDER = curve.getN().shiftRight(1);
	
	public final static String n = "fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141";
	private byte[] publicKey;
	private byte[] privateKey;

	private String n0 = "fddf000000000000000000000000000000000000000000000000000000000000000000";

	public static BigInteger N = new BigInteger(1, n.getBytes());

	/**
	 * 
	 */
	public Secp256k1() {
		super();
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	private KeyPair kp;

	public static final String ALGORITHM = "secp256k1";
	public static final short ALGORITHMNUM = 1;

	public String compressPubKey(BigInteger pubKey) {
		String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
		String pubKeyHex = pubKey.toString(16);
		String pubKeyX = pubKeyHex.substring(0, 64);
		return pubKeyYPrefix + pubKeyX;
	}

	public Map<String, byte[]> getNewKeyPair()
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
		ECGenParameterSpec ecsp;
		ecsp = new ECGenParameterSpec(Secp256k1.ALGORITHM);
		kpg.initialize(ecsp);
		kp = kpg.generateKeyPair();
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		map.put("PublicKey", kp.getPublic().getEncoded());
		map.put("PrivateKey", kp.getPrivate().getEncoded());
		return map;
	}

	@Override
	public byte[] getPublicKeyFromPrivateKey(byte[] pk)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
		BigInteger privateKey = new BigInteger(1, pk);
		BigInteger curvemod = new BigInteger(n, 16);
		privateKey = privateKey.mod(curvemod);
		this.privateKey = privateKey.toByteArray();
		BigInteger publicKey = publicKeyFromPrivate(privateKey);
		String compactPub = compressPubKey(publicKey);
		this.publicKey = Hex.decode(compactPub.getBytes());
		return this.publicKey;
	}

	public byte[] getSignature(byte[] data, byte[] privateKey)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
		ECDomainParameters domain = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH());
		Digest digest = new SHA256Digest();
		digest.update(privateKey, 0, privateKey.length);
		ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(digest));
		ECPrivateKeyParameters pKey = new ECPrivateKeyParameters(new BigInteger(1, privateKey), domain);
		signer.init(true, pKey);
		BigInteger[] sig = signer.generateSignature(data);
		String str11 = toDER(sig[0], sig[1]);
		return Hex.decode(str11);
	}

	public boolean verifySignature(byte[] data, byte[] sign)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		Signature signature;
		signature = Signature.getInstance("ECDSA", "BC");
		signature.update(data);
		return signature.verify(sign);
	}

	@Override
	public byte[] getPrivateKey() {
		return privateKey;
	}

	@Override
	public String getAlgName() {
		return Secp256k1.ALGORITHM;
	}

	/**
	 * Returns public key from the given private key.
	 *
	 * @param privKey the private key to derive the public key from
	 * @return BigInteger encoded public key
	 */
	public BigInteger publicKeyFromPrivate(BigInteger privKey) {
		ECPoint point = publicPointFromPrivate(privKey);

		byte[] encoded = point.getEncoded(false);
		return new BigInteger(1, Arrays.copyOfRange(encoded, 0, encoded.length)); // remove prefix
	}

	/**
	 * Returns public key point from the given private key.
	 *
	 * @param privKey the private key to derive the public key from
	 * @return ECPoint public key
	 */
	public ECPoint publicPointFromPrivate(BigInteger privKey) {
		/*
		 * TODO: FixedPointCombMultiplier currently doesn't support scalars longer than
		 * the group order, but that could change in future versions.
		 */
		if (privKey.bitLength() > CURVE.getN().bitLength()) {
			privKey = privKey.mod(CURVE.getN());
		}
		return new FixedPointCombMultiplier().multiply(CURVE.getG(), privKey);
	}

	/**
	 * Returns public key point from the given curve.
	 *
	 * @param bits representing the point on the curve
	 * @return BigInteger encoded public key
	 */
	public BigInteger publicFromPoint(byte[] bits) {
		return new BigInteger(1, Arrays.copyOfRange(bits, 1, bits.length)); // remove prefix
	}

	public String bytesToHexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (byte b : bytes) {
			String sT = Integer.toString(0xFF & b, 16);
			if (sT.length() < 2)
				buf.append('0');
			buf.append(sT);
		}
		return buf.toString();
	}

	private String toDER(BigInteger r, BigInteger s) {
		String string = Hex.toHexString(r.toByteArray());
		return string.concat(Hex.toHexString(toCanonicalS(s).toByteArray()));
	}

	private BigInteger toCanonicalS(BigInteger s) {
		if (s.compareTo(HALF_CURVE_ORDER) <= 0) {
			return s;
		} else {
			BigInteger bn = new BigInteger(Hex.decode(n0));
			return s.subtract(bn);
		}
	}
}