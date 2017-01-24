package com.example.hjm.commonutils.utils;

import java.text.DecimalFormat;

public class AlLongUtils {

	public static String Base64Encode(String str) {
		return Base64Class.encodeToString(str.getBytes(), 0).replace("\n", "");
	}

	public static String Base64Encode(byte[] b) {
		return Base64Class.encodeToString(b, 0).replace("\n", "");
	}

	public static String Md5Encode(String str) {
		byte[] md5 = MD5code.encode(str.getBytes());
		String base64str = Base64Encode(md5);
		return AlLongUtils.getString(base64str);
	}

	public static String getSignature(String data, String key3) {
		String strTemp = Base64Encode(data) + key3;
		return Md5Encode(strTemp);
	}

	public static String getString(String str) {
		String tempstr = "";
		for (int i = 0; i < str.toCharArray().length; i++) {
			if (str.charAt(i) != '\r' && str.charAt(i) != '\n') {
				tempstr += str.charAt(i);
			}
		}
		return tempstr;
	}

	// ת 1.00 = 100
	public static String FormatAmt(String src) {
		if (src != null && !src.equals("")) {
			DecimalFormat format = new DecimalFormat("#00");
			try {
				return format.format(Double.parseDouble(src) * 100);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				return "";
			}
		}
		return "";
	}

}
