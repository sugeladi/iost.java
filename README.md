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

  // Create HTTPProvider Object
HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);				
				
	// Create IOST Object from HTTPProvider			
				
	IOST iost = new IOST(provider);			

	// Generate a new KeyPair
	KeyPair kp = iost.getNewKeyPair();
	
	// Set publisher name and keyPair for IOST object
	iost.setPublisher(publisher, kp);
	
	// Creating Transaction Object
Transaction transaction = iost.newAccount("test1", kp.getId(), kp.getId(), 10, 10);
				
	
	// Executing transaction
	String json = transaction.sendTx();
	
	// Populating ResponseHash from response
	ResponseHash resp = ResponseHash.getResponseHash(json);		
			
		
// 	use transfer 
	  // Create HTTPProvider Object
HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);				
				
	// Create IOST Object from HTTPProvider			
				
	IOST iost = new IOST(provider);			

	// Generate a new KeyPair
	KeyPair kp = iost.getNewKeyPair();
	
	// Set publisher name and keyPair for IOST object
	iost.setPublisher(publisher, kp);
	
	// Create a Transaction using IOST	callABI method
	Transaction t = iost.callABI("token.iost", "transfer", data);
				
				OR
	   
	// Create a Transaction using IOST transfer method	
	Transaction t = iost.transfer("token.iost", "admin", "10.00", "memo");
			
	// Executing transaction
	String json = transaction.sendTx();
	
	// Getting ResponseHash Object
	ResponseHash resp = ResponseHash.getResponseHash(json);
		

## APIs


