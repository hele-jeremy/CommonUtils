package com.example.hjm.commonutils.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5code {

	public static byte[] encode(byte[] data) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md.digest(data);
	}

}
