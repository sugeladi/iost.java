package blockchain;

import java.io.IOException;

import com.google.gson.Gson;

import iost.json.ContractStorageRequest;
import provider.HTTPProvider;

/**
 * get blockchain info
 */
class Blockchain {

    private HTTPProvider provider;


    protected Blockchain(HTTPProvider provider) {
        this.provider = provider;
    }

    protected String getChainInfo() throws IOException {
        return this.provider.sendGet("getChainInfo");
    }

    protected String getGasInfo() throws IOException {
        return this.provider.sendGet("getGasRatio");
    }

    protected String getRamInfo() throws IOException {
        return this.provider.sendGet("getRAMInfo");
    }

    protected String getBlockByHash(String hash, String complete) throws IOException {
        String api = "getBlockByHash/" + hash + "/" + complete;
        return this.provider.sendGet(api);
    }

    protected String getBlockByNum(String num, String complete) throws IOException {
        String api = "getBlockByNumber/" + num + "/" + complete;
        return this.provider.sendGet(api);
    }

    protected String getNodeInfo() throws IOException {
        return this.provider.sendGet("getNodeInfo");
    }

    protected String getBalance(String address, Boolean useLongestChain) throws IOException {
        String api = "getBalance/" + address + "/" + useLongestChain;
        return this.provider.sendGet(api);
    }

    protected String getTokenBalance(String account, String token, String byLongestChain) throws IOException {
        String api = "getTokenBalance/" + account + "/" + token + "/" + byLongestChain;
        return this.provider.sendGet(api);
    }

    protected String getContract(String id, String byLongestChain) throws IOException {
        String api = "getContract/" + id + "/" + byLongestChain;
        return this.provider.sendGet(api);
    }

    protected String getContractStorage(String contractID, String key, String field, Boolean pending) throws IOException {
        ContractStorageRequest cStorage = new ContractStorageRequest();
        cStorage.setBy_longest_chain(pending);
        cStorage.setField(field);
        cStorage.setKey(key);
        cStorage.setId(contractID);
        String query = new Gson().toJson(cStorage);
        String api = "getContractStorage";
        return this.provider.sendPost(api, query);
    }

    protected String getAccountInfo(String name, String by_longest_chain) throws IOException {
        String api = "getAccount/" + name + "/" + by_longest_chain;
        return this.provider.sendGet(api);
    }
}
