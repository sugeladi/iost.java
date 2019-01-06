package iost.json;

public class ContractObject {

	private String ID;
	private ContractInfo info;
	private String code;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the info
	 */
	public ContractInfo getInfo() {
		return info;
	}
	/**
	 * 
	 */
	public ContractObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(ContractInfo info) {
		this.info = info;
	}
	/**
	 * @param iD
	 * @param info
	 */
	public ContractObject(String iD, ContractInfo info) {
		ID = iD;
		this.info = info;
	}
	
	
}
