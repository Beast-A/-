
	//对称加密
	data = desEncrypt(data, key);
			
	/**
	 * DES加密
	 * @param data 要加密的字符串
	 * @param key 加密盐
	 * @return
	 */
	public static String desEncrypt(String data, String key) {
		String result = "";
		try {
			data = java.net.URLEncoder.encode(data, "utf-8");
			key = trancateRight(key, 8);
			result = toHexString(encrypt(data, key)).toUpperCase();
		} catch (Exception ex) {
			logger.error("des encrypt is error: ", ex);
			return "";
		}
		return result;
	}
	
	private static byte[] encrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("ASCII"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("ASCII"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return cipher.doFinal(message.getBytes("UTF-8"));
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
	
	/**
	 * 去掉字符串右边的字符
	 * @param key 字符串
	 * @param len 去掉字符的长度
	 * @return
	 */
	public static String trancateRight(String key, int len){
		if (value == null || len < 1){
			return "";
		}

		if (value.length() < len){
			return value;
		}

		return StringUtils.substring(value, 0, len);
	}
