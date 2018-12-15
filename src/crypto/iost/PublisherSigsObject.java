package crypto.iost;

public class PublisherSigsObject {

	private String algorithm,public_key,signature;

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @param algorithm
	 * @param public_key
	 * @param signature
	 */
	public PublisherSigsObject(String algorithm, String public_key, String signature) {
		this.algorithm = algorithm.toUpperCase();
		this.public_key = public_key;
		this.signature = signature;
	}
}
