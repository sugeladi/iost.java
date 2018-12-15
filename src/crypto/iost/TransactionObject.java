package crypto.iost;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import crypto.Base58;
import crypto.Codec;

public class TransactionObject {

	private long gasRatio = 0, gasLimit = 0, time = 0, expiration = 0, delay = 0;
	private List<String> signs = new ArrayList<String>();
	private List<ActionObject> actions = new ArrayList<ActionObject>();
	private List<PublisherSigsObject> publisher_sigs = new ArrayList<PublisherSigsObject>();
	private List<String> signers = new ArrayList<String>();
	private List<AmountLimitObject> amount_limit = new ArrayList<AmountLimitObject>();
	private String publisher;

	public TransactionObject(long gasRatio, long gasLimit, long delay) {
		super();
		this.gasRatio = gasRatio;
		this.gasLimit = gasLimit;
		this.delay = delay;
		publisher = "";
	}

	public long getTime() {
		return time;
	}

	public void setTime(long expirationInSecound, long delay) {
		Date date = new Date();
		this.time = (long) (date.getTime() * 1e6);
		this.expiration = (long) (this.time + expirationInSecound * 1e9);
		this.delay = delay;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public long getGasRatio() {
		return gasRatio;
	}

	public byte[] getHash() {
		Utils utility = new Utils();
		return utility.sha3(getBytes(0));
	}

	public String getHexFromBytes(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
			// result.append(" "); // delimiter
		}
		return result.toString();
	}

	public byte[] getPublishHash() {
		Utils utility = new Utils();
		byte[] hs = utility.sha3(getBytes(1));
		return hs;
	}

	public void setGasRatio(long gasRatio) {
		this.gasRatio = gasRatio;
	}

	public long getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(long gasLimit) {
		this.gasLimit = gasLimit;
	}

	public List<AmountLimitObject> getAmount_limit() {
		return amount_limit;
	}

	public void setAmount_limit(AmountLimitObject amount_limit) {
		this.amount_limit.add(amount_limit);
	}

	public List<ActionObject> getActions() {
		return actions;
	}

	public void setAction(ActionObject action) {
		this.actions.add(action);
	}

	public List<String> getSigners() {
		return signers;
	}

	public void setSigner(String signer) {
		this.signers.add(signer);
	}

	public List<String> getSigns() {
		return signs;
	}

	public void addSign(KeyPair keyPair)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		this.signs.add(Base58.encode(keyPair.getSignature(this.getHash())));
	}

	public void addPublishSign(String publisher, KeyPair keyPair)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
		this.publisher = publisher;
		PublisherSigsObject obj = new PublisherSigsObject(keyPair.getIostAlgo().getAlgName(), keyPair.B64PubKey(),
				keyPair.getSignatureAsString(this.getPublishHash()));
		this.publisher_sigs.add(obj);
	}

	public void setSign(String sign) {
		this.signs.add(sign);
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<PublisherSigsObject> getPublisher_sigs() {
		return publisher_sigs;
	}

	public void setPublisher_sigs(PublisherSigsObject publisher_sigs) {
		this.publisher_sigs.add(publisher_sigs);
	}

	public byte[] getBytes(int i) {
		Codec c = new Codec();
		c.pushInt64(this.time);
		c.pushInt64(this.expiration);
		c.pushInt64(this.gasRatio * 100);
		c.pushInt64(this.gasLimit * 100);
		c.pushInt64(this.delay);
		c.arrayStart();

		
		Iterator<String> iterator = this.signers.iterator();
		while (iterator.hasNext()) {
			c.pushString(iterator.next());
		}
		c.arrayEnd();
		c.arrayStart();

		Iterator<ActionObject> iterator2 = this.actions.iterator();

		while (iterator2.hasNext()) {
			ActionObject act = iterator2.next();
			Codec c2 = new Codec();
			c2.pushString(act.getContract());
			c2.pushString(act.getActionName());

			if (act.getData() != null) {
				c2.pushString(act.getData());
			}
			c.pushBytes(c2.getBytes(), false);
		}

		c.arrayEnd();
		c.arrayStart();
		Iterator<AmountLimitObject> iterator3 = this.amount_limit.iterator();
		while (iterator3.hasNext()) {
			AmountLimitObject alo = iterator3.next();
			Codec c2 = new Codec();
			c2.pushString(alo.getToken());
			c2.pushInt64(alo.getValue());
			c.pushBytes(c2.getBytes(), false);
		}
		c.arrayEnd();

		if (i > 0) {
			c.arrayStart();
			Iterator<String> iterator4 = this.signs.iterator();
			while (iterator4.hasNext()) {
				Codec c2 = new Codec();
				c.pushString(iterator4.next());
				c.pushBytes(c2.getBytes(), false);
			}
			c.arrayEnd();
		}

		if (i > 1) {
			// todo
		}

		return c.getBytes();
	}


}
