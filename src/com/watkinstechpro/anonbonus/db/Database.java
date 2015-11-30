package com.watkinstechpro.anonbonus.db;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.codec.binary.Hex;

import com.sun.istack.internal.logging.Logger;
import com.watkinstechpro.anonbonus.userlogininput.UserLogins;

public class Database {
	private static final Logger LOGGER = Logger.getLogger(Database.class);
	public static final Map<String, String> LOGIN_DB = Collections.unmodifiableMap(setUserLogins());
//	private MessageDigest messageDigest;
	private static Database instance;

	public Database(){
//		setUserLogins();
		
//		try {
////			messageDigest = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public Map<String, String> getLoginDB(){
		return LOGIN_DB;
	}
	
	public static final Database getInstance(){
		if(instance == null)
			instance = new Database();
		
		return instance;
	}
	
	public static String encryptPasswd(String passwd){
		String result = null;
		byte[] resultByte = null;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(passwd.getBytes(Charset.forName("UTF8")));
			resultByte = messageDigest.digest();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(resultByte != null)
			result = Hex.encodeHexString(resultByte);
		
		LOGGER.setLevel(Level.INFO);
		LOGGER.info(result);
		return result;
	}
	
	private static Map<String, String> setUserLogins() {
		Map<String, String> userInput = UserLogins.USER_CREDS;
		
		Map<String, String> encryptedUserInput = new HashMap<>(userInput.size());
		
		for(String username:userInput.keySet()){
			encryptedUserInput.put(username, encryptPasswd(userInput.get(username)));
		}
		
		return encryptedUserInput;
	}
}
