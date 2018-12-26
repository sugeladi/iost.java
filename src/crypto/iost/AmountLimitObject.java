package crypto.iost;

public class AmountLimitObject {

    private String token;
    private String value;
    private transient double v;

    public AmountLimitObject(String token, double value) {
        this.token = token;
        this.value = String.valueOf(value);
        this.v = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getValue() {
        return v;
    }

    public void setValue(double value) {
        this.value = String.valueOf(value);
        this.v = value;
    }

    public String getSValue() {
        return this.value;
    }
}
