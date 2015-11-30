package com.watkinstechpro.anonbonus.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Constants {
	public static final char[] VALID_LETTERS = {'A', 'B', 'D', 'E', 'G', 'H', 'M', 'N', 'Q', 'R', 'T'};
	public static final int VALID_LETTERS_LEN = VALID_LETTERS.length;
	public static final char[] VALID_NUMS = {'2', '4', '7'};
	public static final int VALID_NUMS_LEN = VALID_NUMS.length;	
	public static final char[] VALID_SYMBOLS = {'@', '#', '$', '+', '=', '?'};
	public static final int VALID_SYMBOLS_LEN = VALID_SYMBOLS.length;
	
	
	public enum VALID_T{LETTERS, NUMS, SYMBOLS};
	
	public static final VALID_T[][] COMBOS = {
			{VALID_T.LETTERS, VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.LETTERS},
			{VALID_T.NUMS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.NUMS},
			{VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.NUMS},
			{VALID_T.NUMS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.LETTERS},
			{VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS},
			{VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.LETTERS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.NUMS},
			{VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.LETTERS},
			{VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.LETTERS},
			{VALID_T.NUMS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.LETTERS, VALID_T.NUMS},
			{VALID_T.LETTERS, VALID_T.SYMBOLS, VALID_T.SYMBOLS, VALID_T.NUMS, VALID_T.NUMS, VALID_T.LETTERS, VALID_T.NUMS}
	};
	
	public static final int NUM_COMBO_TYPES = COMBOS.length;
	
	public static int codeComboIndex(String code){
		int codeComboIndex = -1;
		if(code == null) return codeComboIndex;
		code = code.trim();
		if(code.isEmpty()) return codeComboIndex;
		
		int len = code.length();
		VALID_T[] combo = new VALID_T[len];
		for(int i=0; i<len; i++){
			combo[i] = getType(code.charAt(i));
			if(combo[i] == null) return codeComboIndex;
		}
		
		return codeComboIndex(combo);
	}
	
	
	
	public static int codeComboIndex(VALID_T[] combo){
		int codeComboIndex = -1;
		
		if(combo == null || combo.length <= 0) return codeComboIndex;
		int len = COMBOS.length;
		boolean isFound = false;
		
		for(int i=0; i<len; i++){
			isFound = true;
			
			int comboLen = COMBOS[i].length;
			if(comboLen != combo.length){
				isFound = false;
				continue;
			}
			
			for(int j=0; j<comboLen -1; j++){
				if(!COMBOS[i][j].equals(combo[j])){
					isFound = false;
					break;
				}
			}
			
			if(isFound && COMBOS[i][comboLen - 1].equals(combo[comboLen - 1])) return i;
		}
		
		return codeComboIndex;
	}
	
	public static VALID_T getType(char value){
		if(isValidLetter(value)) return VALID_T.LETTERS;
		if(isValidNum(value)) return VALID_T.NUMS;
		if(isValidSymbol(value)) return VALID_T.SYMBOLS;
		
		return null;
	}
	
	public static boolean isValidNum(char value){
		if(!Character.isDigit(value)) return false;
		
		for(char val:VALID_NUMS){
			if(value == val) return true;
		}
		
		return false;
	}
	
	public static boolean isValidSymbol(char value){
		if(Character.isWhitespace(value)) return false;
		
		for(char val:VALID_SYMBOLS){
			if(value == val) return true;
		}
		
		return false;
	}
	
	public static boolean isValidLetter(char value){
		if(!Character.isAlphabetic(value)) return false;
		
		for(char val:VALID_LETTERS){
			String upperVal = String.valueOf(value).toUpperCase();
			if(upperVal.charAt(0) == val) return true;
		}
		
		return false;
	}
	
	public static char getRand(VALID_T type){
		double rand = Math.random();
		if(VALID_T.LETTERS.equals(type)) return VALID_LETTERS[(int)(VALID_LETTERS_LEN * rand)];		
		if(VALID_T.NUMS.equals(type)) return VALID_NUMS[(int)(VALID_NUMS_LEN * rand)];
		if(VALID_T.SYMBOLS.equals(type)) return VALID_SYMBOLS[(int)(VALID_SYMBOLS_LEN * rand)];
		
		return 0;
	}

	
	public static int getLength(VALID_T type){
		if(VALID_T.LETTERS.equals(type)) return VALID_LETTERS_LEN;
		if(VALID_T.NUMS.equals(type)) return VALID_NUMS_LEN;
		if(VALID_T.SYMBOLS.equals(type)) return VALID_SYMBOLS_LEN;
		
		return 0;
	}
	
	public static String getLetter(int value){
		if (value < 0) return "";
		
		return VALID_LETTERS[value%VALID_LETTERS_LEN]+"";
	}
	
	public static String getNumber(int value){
		if (value < 0) return "";
		
		return VALID_NUMS[value%VALID_NUMS_LEN]+"";
	}
	
	public static String getSymbol(int value){
		if (value < 0) return "";
		
		return VALID_SYMBOLS[value%VALID_SYMBOLS_LEN]+"";
	}
	
	public static String getCodeElement(VALID_T validType, int value){
		if(VALID_T.LETTERS.equals(validType)) return getLetter(value);
		if(VALID_T.NUMS.equals(validType)) return getNumber(value);
		if(VALID_T.SYMBOLS.equals(validType)) return getSymbol(value);
		
		return null;
	}
	
	public static void renewSession(HttpServletRequest request){
		HttpSession sess = request.getSession();
		
		if (!sess.isNew()) {
				sess = request.getSession(true); // creates a new session
			}
		sess.setMaxInactiveInterval(10 * 60);
	}
	
	
}
