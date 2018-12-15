
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
import crypto.iost.TransactionObject;
import iost.IOST;
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
		
        //String pk = "1rANSfcRzr4HkhbUFZ7L1Zp69JZZHiDDq5v7dNSbbEqeU4jxy3fszV4HGiaLQEyqVpS1dKT9g7zCVRxBVzuiUzB";
		String pk = "4LNkrANP7tzvyy24GKZFRnUPpawLrD6nbrusbB7sJr9Kb2G9oW5dmdjENcFBkYAfKWNqKf7eywLqajxXSRc5ANVi";
		byte[] seckey = Base58.decode(pk);		
		KeyPair kp = new KeyPair(seckey, Ed25519.ALGORITHMNUM);
		IOST iost = new IOST("testaccount",kp);
		/* or can call Abi manually for transfer 
		String data[] = {"iost", "admin", "admin", "10.000", ""};
	    TransactionObject t = iost.callABI("token.iost", "transfer", data);
	   */
		TransactionObject t = iost.transfer("token.iost", "admin", "10.00", "");
		t.setTime(90, 0);
		t.addPublishSign("devel", kp);
		HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);
		
		Transaction transaction = new Transaction(provider);
		String st = transaction.sendTx(t);
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
		String pk = "4LNkrANP7tzvyy24GKZFRnUPpawLrD6nbrusbB7sJr9Kb2G9oW5dmdjENcFBkYAfKWNqKf7eywLqajxXSRc5ANVi";

		byte[] seckey = Base58.decode(pk);
		try {
			KeyPair kp = new KeyPair(seckey, Ed25519.ALGORITHMNUM);
			KeyPair kp2;
				kp2 = new KeyPair(Ed25519.ALGORITHMNUM);
				IOST iost = new IOST("testaccount",kp);
				TransactionObject t = iost.newAccount("test1", kp2.getId(), kp2.getId(), 10, 10);
				t.setTime(90, 0);
				HTTPProvider provider = new HTTPProvider("http://3.0.192.33:30001/", 21);				
				Transaction transaction = new Transaction(provider);
				t.addPublishSign("testaccount", kp2);
				String st = transaction.sendTx(t);
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
			
			
		//	String data[] = {"iost", "admin", "admin", "10.000", ""};
		//	TransactionObject t = iost.callABI("token.iost", "transfer", data);
			
			
			
	}

	public static void main2(String args[]) throws InvalidAlgorithmParameterException {
		String pk = "1rANSfcRzr4HkhbUFZ7L1Zp69JZZHiDDq5v7dNSbbEqeU4jxy3fszV4HGiaLQEyqVpS1dKT9g7zCVRxBVzuiUzB";
		IostAlgo secp = new Ed25519();
		try {
			try {
				byte[] seckey = Base58.decode(pk);
				secp.getPublicKeyFromPrivateKey(seckey);
				System.out.println("KEY: " + Base58.encode(secp.getPublicKey()));
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] sign = secp.getSignature("Test89".getBytes(), secp.getPrivateKey());
			// sign =
			// "TXaKaVgsIwmE6Hycu+VPUa7wD6NpgSpslEk2qSEnDCFGYJTLlWer40+1ydWUPDqhQX5mfSK+6SiByiSf6PyyAQ==".getBytes();
			System.out.println(secp.verifySignature("Test89".getBytes(), sign));
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
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
	

	public static void debugCodec(Codec c) {
		System.out.println("\n **: DEBUG\n" + Hex.toHexString(c.getBytes()) + "\n ***");
		System.out.println("\n **: RAW\n" + Hex.toHexString("transfer".getBytes()) + "\n ***");
		if (true)
			System.exit(0);

	}
}
