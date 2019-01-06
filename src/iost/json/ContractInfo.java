package iost.json;

public class ContractInfo {

	private String lang;
	private String version;
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the abi
	 */
	public Abi[] getAbi() {
		return abi;
	}
	/**
	 * @param abi the abi to set
	 */
	public void setAbi(Abi[] abi) {
		this.abi = abi;
	}
	private Abi[] abi;
}
