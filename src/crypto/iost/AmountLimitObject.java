package crypto.iost;

public class AmountLimitObject {

	private String token;
	private String value;

	public AmountLimitObject(String token, String value) {
		this.token = token;
		this.value = value;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
