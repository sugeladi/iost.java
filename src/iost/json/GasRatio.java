package iost.json;

import com.google.gson.Gson;

public class GasRatio {

	private String lowest_gas_ratio;
	private String median_gas_ratio;
	/**
	 * @return the lowest_gas_ratio
	 */
	public String getLowestGasRatio() {
		return lowest_gas_ratio;
	}
	/**
	 * @param lowest_gas_ratio the lowest_gas_ratio to set
	 */
	public void setLowestGasRatio(String lowestGasRatio) {
		this.lowest_gas_ratio = lowestGasRatio;
	}
	/**
	 * @return the median_gas_ratio
	 */
	public String getMedianGasRatio() {
		return median_gas_ratio;
	}
	/**
	 * @param median_gas_ratio the median_gas_ratio to set
	 */
	public void setMedianGasRatio(String medianGasRatio) {
		this.median_gas_ratio = medianGasRatio;
	}
	public static GasRatio getGasInfo(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, GasRatio.class);
	}
}
