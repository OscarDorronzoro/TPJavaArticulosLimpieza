package util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordManager {

	private static final int ITERATIONS = 1000;
	
	public static String encrypt(String plainTextPassword) {
		char[] pwdChars = plainTextPassword.toCharArray();
		
		try {
			byte[] salt;
			salt = getSalt();
		
			PBEKeySpec spec = new PBEKeySpec(pwdChars, salt, ITERATIONS, 64*8);
			SecretKeyFactory skf;
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

	    	byte[] hash;
			hash = skf.generateSecret(spec).getEncoded();
			
			return ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	    
		return null;
	}
	
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    private static String toHex(byte[] array) {
    	BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        return hex;
    }
    
    public static boolean validatePassword(String plainTextPassword, String encryptedPassword)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
	    String[] parts = encryptedPassword.split(":");
	    int iterations = Integer.parseInt(parts[0]);

	    byte[] salt = fromHex(parts[1]);
	    byte[] hash = fromHex(parts[2]);

	    PBEKeySpec spec = new PBEKeySpec(plainTextPassword.toCharArray(), salt, iterations, hash.length * 8);
	    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    byte[] testHash = skf.generateSecret(spec).getEncoded();

	    int diff = hash.length ^ testHash.length;
	    for(int i = 0; i < hash.length && i < testHash.length; i++) {
	        diff |= hash[i] ^ testHash[i];
	    }
	    return diff == 0;
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
	    byte[] bytes = new byte[hex.length() / 2];
	    for(int i = 0; i < bytes.length ;i++) {
	        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	    }
	    return bytes;
	}
}
