package crypto.iost;

import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

import com.google.gson.Gson;

public class Utils {

	public byte[] sha3(final byte[] input) {
		DigestSHA3 sha3 = new Digest256();
		sha3.update(input);
		return sha3.digest();
	}
	
	public String getJson(String[] data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}

}
