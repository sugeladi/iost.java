package blockchain;

import java.io.IOException;

import com.google.gson.Gson;

import iost.json.ContractStorageFields;
import iost.json.ContractStorageRequest;
import provider.HTTPProvider;


public class BaseBlockChain {

	private HTTPProvider _provider;
	/**
	 * @return the _provider
	 */
	public HTTPProvider get_provider() {
		return _provider;
	}

	/**
	 * @param _provider the _provider to set
	 */
	public void set_provider(HTTPProvider _provider) {
		this._provider = _provider;
	}

	/**
	 * 区块链相关RPC接口的实现
 * @constructor
 * @param {RPC}rpc - 通过rpc生成Blockchain模块
	 */
	
	public BaseBlockChain(HTTPProvider provider) {
		this._provider = provider;
	}
	
	/**
     * 获取区块链整体信息
	 * @throws IOException 
     * @returns response
     */
    public String getChainInfo() throws IOException {
        return this._provider.sendGet( "getChainInfo");
    }
    
    /**
     * * 通过Hash获取区块
     * @param {string}hash - hash in base58
     * @param {boolean}complete - 是否获取完整的block
     * @throws IOException 
     * @returns Block
     */
    public String getBlockByHash(String hash, String complete) throws IOException {
        String api = "getBlockByHash/" + hash + "/" + complete;
        return this._provider.sendGet( api);
    }
    

    /**
     * 通过区块高度获取区块
     * @param {number}num - 区块高度
     * @param {boolean}complete - 是否获取完整的block
     * @throws IOException 
     * @returns {response}
     */
    public String getBlockByNum(String num,String complete) throws IOException {
        String api = "getBlockByNumber/" + num + "/" + complete;
        return this._provider.sendGet( api);
    }
    
    /**
     * 
     * @returns response
     */
    public String getGasInfo() throws IOException {
        return this._provider.sendGet("getGasRatio");
    }
    
    public String getRamInfo() throws IOException {
        return this._provider.sendGet("getRAMInfo");
    }
    

    
    /**
     * 获取某个用户的余额
     * @param address
     * @param byLongestChain
     * @throws IOException 
     * @returns {balance}
     */
    public String getBalance(String account,String token, String byLongestChain) throws IOException // TODO 有问题，get token balance
    {
        String api = "getTokenBalance/" + account + "/" + token + "/" + byLongestChain;
        return this._provider.sendGet( api);
    }
    
    /**
     * 获取某个用户的余额
     * @param address
     * @param tokenSymbol
     * @param useLongestChain
     * @throws IOException 
     * @returns {json String}
     */
    public String getToken721Balance(String address, String tokenSymbol, boolean useLongestChain) throws IOException
    {
        String api = "getToken721Balance/" + address + "/" + tokenSymbol + "/" + useLongestChain;
        return this._provider.sendGet(api);
    }

    /**
     * 获取某个token721类型token的 metadata
     * @param tokenSymbol
     * @param tokenID
     * @param useLongestChain
     * @throws IOException 
     * @returns {json String}
     */
    public String getToken721Metadata(String tokenSymbol, String tokenID, boolean useLongestChain) throws IOException
    {
        String api = "getToken721Metadata/" + tokenSymbol + "/" + tokenID + "/" + useLongestChain;
        return this._provider.sendGet(api);
    }
    /**
     * 获取某个token721类型token的 owner
     * @param tokenSymbol
     * @param tokenID
     * @param useLongestChain
     * @throws IOException 
     * @returns {json String}
     */
    public String getToken721Owner(String tokenSymbol, String tokenID, boolean useLongestChain) throws IOException
    {
        String api = "getToken721Owner/" + tokenSymbol + "/" + tokenID + "/" + useLongestChain;
        return this._provider.sendGet(api);
    }

    /**
     * 获取智能合约
     * @param {string}id - 智能合约的ID
     * @throws IOException 
     * @returns {response}
     */
    public String getContract(String id, String byLongestChain) throws IOException {
        String api = "getContract/" + id + "/" + byLongestChain;
        return this._provider.sendGet( api);
    }

    /**
     * * 获取智能合约下的某个键值
     * @param {string}contractID - 智能合约ID
     * @param {string}key - 需查询的key
     * @param {string}field - 需查询的field
     * @param {boolean}pending - 是否从最长链上查询
     * @throws IOException 
     * @returns {String}
     */
    public String getContractStorage(String contractID, String key, String field, boolean pending) throws IOException {
    	ContractStorageRequest cStorage = new ContractStorageRequest();
    	cStorage.setBy_longest_chain(pending);
    	cStorage.setField(field);
    	cStorage.setKey(key);
    	cStorage.setId(contractID);
    	String query = new Gson().toJson(cStorage);
        String api = "getContractStorage";
        return this._provider.sendPost( api, query);
    }


    /**
     * 获取智能合约下的fields集合
     * @param {string}contractID - 智能合约ID
     * @param {string}fields - 需查询的fields
     * @param {boolean}pending - 是否从最长链上查询
     * @throws IOException 
     * @returns {json String}
     */
    public String getContractStorageFields(String contractID, String fields, boolean pending) throws IOException {
        ContractStorageFields csfields = new ContractStorageFields(contractID, fields, pending);
        String query = new Gson().toJson(csfields);
        String api = "getContractStorageFields";
        return this._provider.sendPost(api, query);
    }


    /**
     * 获�?�account信�?�
     * @param {string}id - 用户�??
     * @param {boolean}reversible - 是�?�从�?�逆链上查询
     * @throws IOException 
     * @returns {response}
     */
    public String getAccountInfo(String name,String by_longest_chain) throws IOException {
        String api = "getAccount/" + name + "/" + by_longest_chain;
        return this._provider.sendGet( api);
    }
    
    /**
     * 获取当前Gas费率
     * @throws IOException 
     * @returns {promise}
     */
    public String getGasRatio() throws IOException {
        return this._provider.sendGet( "getGasRatio");
    }

    /**
     * 获取预估的gas消耗
     * @returns {number}
     */
    public long getGasUsage(String actionName) {
        switch (actionName) {
            case "transfer":
                return 7800;
            case "newAccount":
                return 115000;
        }
		return 0;
    }
}
