package com.wym.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class Md5Utils {

	public static String crateSalt() {
		return UUID.randomUUID().toString();
	}
	
	public static String md5Password(String password,String salt) {
		
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
		
			byte[] md5 = digest.digest((password+salt).getBytes());
		
			byte[] encode = Base64.getEncoder().encode(md5);
		    return new String(encode);
		
		
		}catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("加密失败");
		}
		
	}
	
	
	
}
