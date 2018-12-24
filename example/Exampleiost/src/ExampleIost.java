import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.bouncycastle.util.encoders.Hex;

import blockchain.Blockchain;
import blockchain.Transaction;
import crypto.Base58;
import crypto.Codec;
import crypto.Ed25519;
import crypto.IostAlgo;
import crypto.iost.KeyPair;
import iost.json.ResponseHash;
import provider.HTTPProvider;

class ExampleIost {

public static void main(String args[]) {
		
		ExampleIost algo = new ExampleIost();
		algo.blockchain();
		algo.transfer();
		algo.newAccount();
	}
	/*
	 * Test BlockChain
	 */
	public void blockchain() {
		HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		Blockchain blockchain = new Blockchain(provider);
		try {
			String json = blockchain.getChainInfo();
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * transfer tokens
	 */
	
	public void transfer() {
		
	try {
		
       HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		IOST iost = new IOST(100, 100000, 0, provider);		
		String publisher = "test";		
		String pk = "4LNkrANP7tzvyy24GKZFRnUPpawLrD6nbrusbB7sJr9Kb2G9oW5dmdjENcFBkYAfKWNqKf7eywLqajxXSRc5ANVi";
		KeyPair kp = iost.getKeyPair(pk);
		iost.setPublisher(publisher, kp);
		Transaction transaction = iost.transfer("token.iost", "admin", "10.00", "");
		String st = transaction.sendTx();		
		System.out.println(st);
		
	} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeySpecException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SignatureException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	

	/**
	 * Create New Account
	 */
	public void newAccount() {
		try {
			String publisher = "";
				HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);				
				IOST iost = new IOST(provider);
				KeyPair kp = iost.getNewKeyPair();
				iost.setPublisher(publisher, kp);
				Transaction transaction = iost.newAccount("test1", kp.getId(), kp.getId(), 10, 10);
				String st = transaction.sendTx();
				ResponseHash resp = ResponseHash.getResponseHash(st);
				System.out.println(resp.getHash());
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
	}
}