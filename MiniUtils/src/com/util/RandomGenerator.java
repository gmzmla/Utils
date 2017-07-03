package com.util;

import java.util.Random;

/**
 * 验证码生成工具
 */
public class RandomGenerator {

	private static String range = "0123456789abcdefghijklmnopqrstuvwxyz";

	private static final String zf = "~!@#$%^&*";

	private final Random random = new Random();
	
	/**
	 * 生成8位
	 * @return
	 */
	public static synchronized String getRandomString() {
		Random random = new Random();

		StringBuffer result = new StringBuffer();

		for ( int i = 0 ; i < 8 ; i++ ) {
			result.append(range.charAt(random.nextInt(range.length())));
		}

		return result.toString();
	}


	/**
	 * 生成seqId，12位随机数
	 * @return
	 */
	public String generateSeqId() {
		String result = "";
		for ( int i = 0 ; i < 2 ; i++ ) {
			result = String.valueOf(random.nextInt(1000000)) + result;
			for ( ; result.length() < (i + 1) * 6 ; result = "0" + result ) {
				;
			}
		}
		return result;
	}


	/**
	 * 生成4位随机数
	 */
	public static String generateMobileCode() {
		Random random = new Random();
		String result = "";
		for ( int i = 0 ; i < 4 ; i++ ) {
			result += random.nextInt(10);
		}
		return result;
	}


	/**
	 * 获取验证码（纯数字）
	 * <p>
	 * @param num：验证码位数
	 * @return 
	 */
	public static String getCode( final int num ) {
		String code = "";
		Random random = new Random();
		for ( int i = 0 ; i < num ; i++ ) {
			code += random.nextInt(10);
		}
		return code;
	}


	public static String getGenerateCheckCode( int length ) {
		String val = "";
		Random random = new Random();
		for ( int i = 0 ; i < length ; i++ ) {
			int charOrNum = random.nextInt(3) % 3; // 输出字母还是数字

			if ( charOrNum == 0 ) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ( charOrNum == 1 ) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			} else if ( charOrNum == 2 ) {
				int rand = (int) (Math.random() * zf.length());
				val += String.valueOf(zf.charAt(rand));
			}
		}
		return val;
	}
}
