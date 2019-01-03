package blockchain;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import iost.json.Account;
import iost.json.BlockFromApi;
import iost.json.ChainInfo;
import iost.json.Contract;
import iost.json.ContractStorage;
import iost.json.ContractStorageData;
import iost.json.GasRatio;
import iost.json.NodeInfo;
import iost.json.RamInfo;
import iost.json.TokenBalance;
import provider.HTTPProvider;

/**
 * API of IOST Blockchain
 */
public class Api extends Blockchain {

    /**
     * @param provider - network provider
     */
    public Api(HTTPProvider provider) {
        super(provider);
    }

    public NodeInfo getNodeInfoObject(long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = this.getNodeInfo();
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return NodeInfo.getNodeInfo(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public ChainInfo getChainInfoObject(long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = this.getChainInfo();
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return ChainInfo.getChainInfo(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public GasRatio getGasRatioObject(long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = this.getGasInfo();
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return GasRatio.getGasInfo(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public RamInfo getRamInfoObject(long intervalInMillis, int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = this.getRamInfo();
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return RamInfo.getRamInfo(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public BlockFromApi getBlockByHash(String hash, String complete, long intervalInMillis, int times)
            throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getBlockByHash(hash, complete);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return BlockFromApi.getBlock(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public BlockFromApi getBlockByNumber(String num, String complete, long intervalInMillis, int times)
            throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getBlockByNum(num, complete);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return BlockFromApi.getBlock(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public Account getAccount(String name, String by_longest_chain, long intervalInMillis, int times)
            throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getAccountInfo(name, by_longest_chain);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return Account.getAccount(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public TokenBalance getTokenBalance(String account, String token, String byLongestChain, long intervalInMillis,
                                        int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getTokenBalance(account, token, byLongestChain);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return TokenBalance.getTokenBalance(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public Contract getContract(String id, String byLongestChain, long intervalInMillis,
                                int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getContract(id, byLongestChain);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        if (resStr.startsWith("{")) {
            return Contract.getContract(resStr);
        } else {
            throw new TimeoutException();
        }
    }

    public ContractStorageData getContractStorageData(String contractID, String field, String key, Boolean pending, long intervalInMillis,
                                                      int times) throws TimeoutException {
        String resStr = "";
        for (int i = 0; i < times; i++) {
            try {
                resStr = super.getContractStorage(contractID, key, field, pending);
            } catch (IOException e) {
                try {
                    Thread.sleep(intervalInMillis);
                    continue;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            break;
        }
        System.out.println(resStr);
        if (resStr.startsWith("{")) {
            return ContractStorageData.getContractStorageData(resStr);
        } else {
            throw new TimeoutException();
        }
    }
}
