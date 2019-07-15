package com.superman.shiro.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
* @Author: Ningsc
* @Date: 2019/7/15
* @Description:  加密工具
* @Param:
* @return:
*/
public class MD5Utils {

	public static final String ENCRYPT_TYPE = "md5";

	public static final int HASH_ITERATIONS = 1024;

	/**
	 * MD5加密：
	 * 使用SimpleHash类对原始密码进行加密。
	 * 第一个参数代表使用MD5方式加密
	 * 第二个参数为原始密码
	 * 第三个参数为盐值，
	 * 第四个参数为加密次数
	 * 最后用toHex()方法将加密后的密码转成String
	 * */
	public static String encrypt(String pswd,ByteSource salt) {

		String newPassword = new SimpleHash(ENCRYPT_TYPE, pswd, salt, HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static void main(String[] args) {
		
		//System.out.println(MD5Utils.encrypt("admin", "1"));
	}

}
