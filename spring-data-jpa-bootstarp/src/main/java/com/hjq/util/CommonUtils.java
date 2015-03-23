package com.hjq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class CommonUtils {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static String Md5(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("md5");
        md.update(input.getBytes());
        byte[] output = md.digest();
		return bytesToHex(output);
	}
	
	public static String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
}
