package crypto.iost;

import java.security.MessageDigest;

import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

import com.google.gson.Gson;

public class Utils {

	public String sha3AsString(final byte[] input) {
        final DigestSHA3 sha3 = new Digest256();
        sha3.update(input);
        return hashToString(sha3);
    }

	public byte[] sha3(final byte[] input) {
		 final DigestSHA3 sha3 = new Digest256();
        sha3.update(input);
        return sha3.digest();
    }
	
    public String hashToString(MessageDigest hash) {
        return hashToString(hash.digest());
    }

    public String hashToString(byte[] hash) {
        StringBuffer buff = new StringBuffer();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }

        return buff.toString();
    }
    public String getJson(String[] data) {
    	Gson gson = new Gson();
    	return gson.toJson(data);
    }
    public String getHexFromBytes(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(String.format("%02x",b));
		}
		return result.toString();
	}
}
