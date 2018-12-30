package iost.json;

import com.google.gson.Gson;

public class BlockFromApi {

	private String status;
	private Block block;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}
	/**
	 * @param block the block to set
	 */
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public static BlockFromApi getBlock(String json) {
		Gson gson = new Gson();
        return gson.fromJson(json, BlockFromApi.class);
	}
}
