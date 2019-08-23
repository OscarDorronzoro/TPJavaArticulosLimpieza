package util;

import org.apache.commons.codec.digest.DigestUtils;
public class PasswordManager {

	public static String encriptar(String plainText) {
		return DigestUtils.sha512Hex(plainText);
	}
}
