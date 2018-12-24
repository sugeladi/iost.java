package blockchain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import provider.HTTPProvider;


public class Blockchain {

	private HTTPProvider _provider;
	/**
	 * 区�?�链相关RPC接�?�的实现
	 * @Stringructor
	 * @param {RPC}rpc - 通过rpc生�?Blockchain模�?�
	 */
	
	public Blockchain(HTTPProvider provider) {
		this._provider = provider;
	}
	
	/**
     * 获�?�区�?�链整体信�?�
	 * @throws IOException 
     * @returns response
     */
    public String getChainInfo() throws IOException {
        return this._provider.sendGet( "getChainInfo");
    }
    
    /**
     * 通过Hash获�?�区�?�
     * @param {string}hash - hash in base58
     * @param {boolean}complete - 是�?�获�?�完整的block
     * @throws IOException 
     * @returns Block
     */
    public String getBlockByHash(String hash, String complete) throws IOException {
        String api = "getBlockByHash/" + hash + "/" + complete;
        return this._provider.sendGet( api);
    }
	

    /**
     * 通过区�?�高度获�?�区�?�
     * @param {number}num - 区�?�高度
     * @param {boolean}complete - 是�?�获�?�完整的block
     * @throws IOException 
     * @returns {response}
     */
    public String getBlockByNum(String num,String complete) throws IOException {
        String api = "getBlockByNum/" + num + "/" + complete;
        return this._provider.sendGet( api);
    }
    
    /**
     * 获�?�当�?节点信�?�
     * @throws IOException 
     * @returns {node info} - 当�?节点的信�?�
     */
    public String getNodeInfo() throws IOException {
        return this._provider.sendGet("getNodeInfo");
    }

    /**
     * 获�?��?个用户的余�?
     * @param address
     * @param useLongestChain
     * @throws IOException 
     * @returns {balance}
     */
    public String getBalance(String address, Boolean useLongestChain) throws IOException // TODO 有问题，get token balance
    {
        String api = "getBalance/" + address + "/" + useLongestChain;
        return this._provider.sendGet( api);
    }

    /**
     * 获�?�智能�?�约
     * @param {string}id - 智能�?�约的ID
     * @throws IOException 
     * @returns {response}
     */
    public String getContract(String id) throws IOException {
        String api = "getContract/" + id;
        return this._provider.sendGet( api);
    }

    /**
     * 获�?�智能�?�约下的�?个键值
     * @param {string}contractID - 智能�?�约ID
     * @param {string}key - 需查询的key
     * @param {string}field - 需查询的field
     * @param {boolean}pending - 是�?�从最长链上查询
     * @throws IOException 
     * @returns {String}
     */
    public String getContractStorage(String contractID, String key, String field,Boolean pending) throws IOException {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", contractID);
    	map.put("key", key);
    	map.put("field", field);
    	map.put("by_longest_chain", pending.toString());
    	String query = new Gson().toJson(map);
        String api = "getContractStorage";
        return this._provider.sendPost( api, query);
    }

    /**
     * 获�?�account信�?�
     * @param {string}id - 用户�??
     * @param {boolean}reversible - 是�?�从�?�逆链上查询
     * @throws IOException 
     * @returns {response}
     */
    public String getAccountInfo(String id,boolean reversible) throws IOException {
        String api = "getAccount/" + id + "/" + (reversible? 1: 0);
        return this._provider.sendGet( api);
    }
}
