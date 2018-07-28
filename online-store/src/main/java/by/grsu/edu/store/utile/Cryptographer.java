package by.grsu.edu.store.utile;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.ovea.crypto.HEX;

public class Cryptographer {
	private static final String ALGORITHM_NAME = "HmacSHA256";

	public String encode(String value, String key) {
		try {
			Mac sha256_HMAC = Mac.getInstance(ALGORITHM_NAME);
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), ALGORITHM_NAME);
			sha256_HMAC.init(secret_key);
			return HEX.encodeHexString(sha256_HMAC.doFinal(value.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}
}

