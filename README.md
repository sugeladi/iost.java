new Config(gasRatio, gasLimit, delay, expiration, defaultLimit);# iost.java
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
		
## Creating a new account

  // Create HTTPProvider Object
HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21);				
				
	// Create a confiuartion.
	Config config = new Config(gasRatio, gasLimit, delay, expiration, amountLimit);
				
	// Create IOST Object from Config and HTTPProvider			
				
	IOST iost = new IOST(config, provider);		
	
	// init account
	Account account = new Account("account_name");	

	KeyPair kp = new KeyPair(
					Base58.decode(
							"Your Base58 encoded private keys"),
					Ed25519.ALGORITHMNUM);
		//Add keyPair to the account
			account.addKeyPair(kp, "owner");
			account.addKeyPair(kp, "active");
			
	// Generate a new KeyPair for new accountTransactionObject 
	KeyPair kp = new KeyPair(Ed25519.ALGORITHMNUM);
	
	
	// Initiating Transaction Object
	TransactionObject transactionObject = iost.newAccount("test1", "admin", newKP.getId(), newKP.getId(), 1024, 10);
	
	// Signing transaction object
	transactionObject = account.signTx(transactionObject);
				
	
	// Initiating transaction handler
	TransactionHandler transactionHandler = new TransactionHandler(provider, transactionObj, 30, 3);
	
	// sending transaction and getting receipt 
		TxReceipt receipt = transactionHandler.sendTx();		
			
		
## TRANSFER 


	// Create a Transaction using IOST transfer method	
	TransactionObject transactionObj = iost.transfer("iost", "admin", "admin", "10.000", "");
	
	// Signing transaction object
	transactionObj = account.signTx(transactionObj);
			
	TransactionHandler transactionHandler = new TransactionHandler(provider, transactionObj);
	
	TxReceipt receipt = transactionHandler.sendTx();
		

## APIs


HTTPProvider provider = new HTTPProvider("http://127.0.0.1:30001/", 21, 500, 3)
	Api api = new Api(provider);
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
		