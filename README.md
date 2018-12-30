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

	HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21, 500, 3)
		Blockchain blockchain = new Blockchain(provider);
		try {
			NodeInfo info = api.getNodeInfoObject(500, 2);
			System.out.println(info.getGitHash());
			System.out.println(info.getNetwork().getId());
			ChainInfo chain = api.getChainInfoObject(500, 2);
			System.out.println(chain.getLibBlockHash());
			System.out.println(chain.getHeadBlockHash());
			GasRatio gasR = api.getGasRatioObject(500, 3);
			System.out.println(gasR.getLowestGasRatio());
			System.out.println(gasR.getMedianGasRatio());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
// use create a new account

  // Create HTTPProvider Object
HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21, 500, 3);				
				
	// Create IOST Object from HTTPProvider			
				
	IOST iost = new IOST(provider);			

	// Generate a new KeyPair
	KeyPair kp = iost.getNewKeyPair();
	
	// Set publisher name and keyPair for IOST object
	iost.setPublisher(publisher, kp);
	
	// Creating Transaction Object
TransactionObject transactionObj = iost.newAccount("test1", kp.getId(), kp.getId(), 10, 10);
				
	
	// Executing transaction
	Transaction transaction = new Transaction(provider, transactionObj);
		TxReceipt receipt = transaction.sendTx();		
		System.out.println(receipt.getMessage());		
			
		
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
	TransactionObject transactionObj = iost.callABI("token.iost", "transfer", data);
				
				OR
	   
	// Create a Transaction using IOST transfer method	
	TransactionObject transactionObj = iost.transfer("token.iost", "admin", "10.00", "memo");
			
	Transaction transaction = new Transaction(provider, transactionObj);
	TxReceipt receipt = transaction.sendTx();		
	System.out.println(receipt.getMessage());
		

## APIs


