# iost.java
IOST Java SDK

Example eclipse project is included inside example folder

Java SDK of IOST

## Installation
Using maven
```
mvn package 
```

## Usage
```

// use Blockchain

	HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);
		Blockchain blockchain = new Blockchain(provider);
		try {
			String json = blockchain.getChainInfo();
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
// use create a new account

	// Generate a new KeyPair
	KeyPair kp = new KeyPair(Ed25519.ALGORITHMNUM);
	
	// Creating Transaction Object
	TransactionObject t = iost.newAccount("test1", kp.getId(), kp.getId(), 10, 10);
	t.setTime(90, 0);
	
	// Creating Transaction
	HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);				
	Transaction transaction = new Transaction(provider);
	
	// Signing Transaction
	t.addPublishSign("testaccount", kp);
	
	// Executing transaction
	String st = transaction.sendTx(t);
	
	// Populating ResponseHash from response
	ResponseHash resp = ResponseHash.getResponseHash(st);				
		
// 	use transfer tokens
	String pk = "Base58 encoded private keys";
	byte[] seckey = Base58.decode(pk);		
	KeyPair kp = new KeyPair(seckey, Ed25519.ALGORITHMNUM);
	
	IOST iost = new IOST("testaccount",kp);
	String data[] = {"iost", "admin", "admin", "10.000", ""};
	
	// Create a Transaction using IOST	callABI method
	TransactionObject t = iost.callABI("token.iost", "transfer", data);
				
				OR
	   
	// Create a Transaction using IOST transfer method	
	TransactionObject t = iost.transfer("token.iost", "admin", "10.00", "");
			

  // Publish transaction
	t.setTime(90, 0);
	t.addPublishSign("devel", kp);
	HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);
	
// Executing Transaction
	Transaction transaction = new Transaction(provider);
	String st = transaction.sendTx(t);
	System.out.println(st);
	
	// Getting ResponseHash Object
	ResponseHash resp = ResponseHash.getResponseHash(st);
		

## APIs


