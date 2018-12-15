package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Tx {

	private long gasRatio;
	private long gasLimit;
	private List<String> actions;
	private List<String> signers;
	private List<String> signs;
	private List<String> publisher_sigs;
	private String publisher;
	private List<String> amount_limit;
	private double time;
	private double expiration;
	private long delay;

	public Tx(long gasRatio, long gasLimit) {
		this.gasRatio = gasRatio;
		this.gasLimit = gasLimit;
		this.actions = new ArrayList<String>();
		this.signers = new ArrayList<String>();
		this.signs = new ArrayList<String>();
		this.publisher = "";
		this.publisher_sigs = new ArrayList<String>();
		this.amount_limit = new ArrayList<String>();
	}

	public void addSigner(String name, String permission) {
		this.signers.add(name + "@" + permission);
	}

	public void addLimit(String token, Long amount) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("amount", amount.toString());
		String query = new Gson().toJson(map);
		this.amount_limit.add(query);
	}
	
	public void addAction(String contract, String abi, String args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("contract", contract);
		map.put("abi", abi);
		map.put("args", args);
		String query = new Gson().toJson(map);
		this.actions.add(query);
    }
	
	public void setTime(long expirationInSecound, long delay) {
        Date date = new Date();
        this.time = date.getTime() * 1e6;
        this.expiration = this.time + expirationInSecound * 1e9;
        this.delay = delay;
    }
}