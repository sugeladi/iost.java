package iost.json;

public class Token721Data {

	private String name;
	private String hp;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 */
	public Token721Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param hp
	 */
	public Token721Data(String name, String hp) {
		this.name = name;
		this.hp = hp;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the hp
	 */
	public String getHp() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}
}
