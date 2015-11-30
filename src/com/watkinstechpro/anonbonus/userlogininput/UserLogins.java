package com.watkinstechpro.anonbonus.userlogininput;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserLogins {
	public static final Map<String, String> USER_CREDS = Collections.unmodifiableMap(getUserCreds());

	public static Map<String, String> getUserCreds(){
		Map<String, String> usrCreds = new HashMap<>();
		usrCreds.put("randomP", "G@ff@w!");
		usrCreds.put("guest", "L@ugh!ng");
		
		return usrCreds;
	}
}
