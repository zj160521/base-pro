package com.simes.core.util.crypto;

import com.simes.core.exception.SystemException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description: 加密模式：ECB, 填充：pcks7 ,数据块：128 输出：hex
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class AesUtil {

	private static final int LENGTH = 16;
	private static final int TWO = 2;
	/**
	 * 默认密钥
	 */
	public static final String DEFAULT_KEY = "De985sdfaJuy78ad";

	private static final String KEY_AES = "AES";

	public static String encrypt(String src, String key) throws Exception {
		if (key == null || LENGTH != key.length()) {
			throw new Exception("key不满足条件");
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(src.getBytes());
		return byte2hex(encrypted);
	}

	public static String encrypt(String src){
		try {
			return encrypt(src,DEFAULT_KEY);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static String decrypt(String src) {
		try {
			return decrypt(src,DEFAULT_KEY);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	public static String decrypt(String src, String key) throws Exception {
		if (key == null || LENGTH != key.length()) {
			throw new Exception("key不满足条件");
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encrypted1 = hex2byte(src);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int length = strhex.length();
		if (length % TWO == 1) {
			return null;
		}
		byte[] b = new byte[length / 2];
		for (int i = 0; i != length / TWO; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs.append("0").append(stmp);
			} else {
				hs = hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}

}