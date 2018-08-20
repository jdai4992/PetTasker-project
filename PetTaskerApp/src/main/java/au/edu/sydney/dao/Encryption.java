package au.edu.sydney.dao;

/** Note: The code in this file is from
 * 	https://www.roseindia.net/hibernate/hibernate4/spring_hibernate_encrypted_password.shtml
 */

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {
	public static String encrypt(String source) {
		String md5 = null;
			try {
				MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
				mdEnc.update(source.getBytes(), 0, source.length());
				md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
			} catch (Exception ex) {
				return null;
			}
			return md5;
	}
}