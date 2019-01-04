package blockchain;

import java.io.IOException;

import provider.HTTPProvider;

/**
 * 查询当前节点信息的接口
 * @constructor
 * @param {HTTPProvider}provider - 通过rpc生成Net模块
 */

public class Net {

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
	 * @param _provider
	 */
	public Net(HTTPProvider _provider) {
		this._provider = _provider;
	}
	
	   /**
     *获取当前节点信息
     * @throws IOException 
     * @returns {node info json string} 
     */
    public String getNodeInfo() throws IOException {
        return this._provider.sendGet("getNodeInfo");
    }
	
}
