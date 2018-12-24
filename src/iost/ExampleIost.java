package iost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import blockchain.Blockchain;
import blockchain.Transaction;
import crypto.Base58;
import crypto.Ed25519;
import crypto.iost.KeyPair;
import iost.json.ResponseHash;
import provider.HTTPProvider;

class ExampleIost {

    public static void main(String args[]) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, UnsupportedEncodingException {
        ExampleIost algo = new ExampleIost();
    	algo.transfer();
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
	
	public void transfer() {
		
	try {
		
       HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		IOST iost = new IOST(1, 100000, 0, provider);		
		String publisher = "testaccount";		
		String pk = "4LNkrANP7tzvyy24GKZFRnUPpawLrD6nbrusbB7sJr9Kb2G9oW5dmdjENcFBkYAfKWNqKf7eywLqajxXSRc5ANVi";
		byte[] pks = Base58.decode(pk);		
		KeyPair kp = new KeyPair(pks,Ed25519.ALGORITHMNUM);		
		iost.setPublisher(publisher, kp);
		Transaction transaction = iost.transfer("iost", "admin", "10.000", "");
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
				KeyPair kp = iost.getNewKeyPair(Ed25519.ALGORITHMNUM);
				System.out.println(kp.getId());
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