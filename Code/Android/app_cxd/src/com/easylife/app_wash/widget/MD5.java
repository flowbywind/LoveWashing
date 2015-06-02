package com.easylife.app_wash.widget;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static final String MD5 = "MD5";

	/**
	 * 
	 * md5 创建MD5信息摘要
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 */

	// 半加密
	public static String getMD5(String str) {
		MessageDigest md5;
		byte[] m = null;
		try {
			md5 = MessageDigest.getInstance(MD5);// 实现MD5算法的 MessageDigest 对象
			md5.update(str.getBytes());// 使用str字节更新摘要
			m = md5.digest();// 通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < m.length; i++) {
			sb.append(m[i]);
		}
		return sb.toString();
	}

	// 标准加密

	public static String getMD5HexString(String content) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] md5Keys = digest.digest(content.getBytes("utf-8"));
			String hexString = toHexString(md5Keys).toUpperCase();
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}

}
