package iost.json;

import com.google.gson.Gson;

public class RamInfo {

	private long available_ram;
	private long used_ram;
	private long total_ram;
	private double buy_price;
	private double sell_price;
	private String available;
	
	/**
	 * @return the available_ram
	 */
	public long getAvailableRam() {
		return available_ram;
	}
	/**
	 * @param available_ram the available_ram to set
	 */
	public void setAvailableRam(long availableRam) {
		this.available_ram = availableRam;
	}
	/**
	 * @return the used_ram
	 */
	public long getUsedRam() {
		return used_ram;
	}
	/**
	 * @param used_ram the used_ram to set
	 */
	public void setUsedRam(long usedRam) {
		this.used_ram = usedRam;
	}
	/**
	 * @return the total_ram
	 */
	public long getTotalRam() {
		return total_ram;
	}
	/**
	 * @param total_ram the total_ram to set
	 */
	public void setTotalRam(long totalRam) {
		this.total_ram = totalRam;
	}
	/**
	 * @return the buy_price
	 */
	public double getBuyPrice() {
		return buy_price;
	}
	/**
	 * @param buy_price the buy_price to set
	 */
	public void setBuyPrice(double buyPrice) {
		this.buy_price = buyPrice;
	}
	/**
	 * @return the sell_price
	 */
	public double getSellPrice() {
		return sell_price;
	}
	/**
	 * @param sell_price the sell_price to set
	 */
	public void setSellPrice(double sellPrice) {
		this.sell_price = sellPrice;
	}
	
	public static RamInfo getRamInfo(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, RamInfo.class);
	}
	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}
}
