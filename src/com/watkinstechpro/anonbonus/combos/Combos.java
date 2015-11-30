package com.watkinstechpro.anonbonus.combos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.watkinstechpro.anonbonus.constants.Constants;
import com.watkinstechpro.anonbonus.constants.Constants.VALID_T;

public class Combos {
	private final static String FIRST_DATE = "20150831";
	private final static String DATE_FORMAT = "yyyyMMdd";
	private final static Calendar FIRST_DATE_CAL = getCalendarDate(FIRST_DATE);
	private final static int FIRST_DATE_WEEK_OF_YEAR = getWeekOfYear(FIRST_DATE);
	public static int VALID_CODE_INDEX = getWeekOfYear();

	public static void increaseValidCodeIndex() {
		VALID_CODE_INDEX = (getWeekOfYear()) % Constants.NUM_COMBO_TYPES;
	}

	public static int getWeekOfYear() {
		final Calendar cal = Calendar.getInstance();
		int curWeek = cal.get(Calendar.WEEK_OF_YEAR);
		int curYear = cal.get(Calendar.YEAR);
		int firstYear = FIRST_DATE_CAL.get(Calendar.YEAR);

		int offset = 52 * (curYear - firstYear);
		return (curWeek - FIRST_DATE_WEEK_OF_YEAR + offset);
	}

	@SuppressWarnings("finally")
	public static Calendar getCalendarDate(String dateStr) {
		Calendar cal = null;

		try {
			final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
			Date date = df.parse(dateStr);
			cal = Calendar.getInstance();
			cal.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return cal;
		}
	}

	@SuppressWarnings("finally")
	public static int getWeekOfYear(String dateStr) {
		int weekOfYr = -999;

		try {
			final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
			Date date = df.parse(dateStr);
			final Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			weekOfYr = cal.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return weekOfYr;
		}
	}

	public static List<String>[] generateCodes() {

		VALID_T[][] combos = Constants.COMBOS;

		int combosLen = combos.length;

		List<String>[] codes = new List[combosLen];

		for (int i = 0; i < combosLen; i++) {
			int immNumCombos = getNumCombos(combos[i]);
			codes[i] = new ArrayList<>(immNumCombos);
			int comboLen = combos[i].length;
			int[] indices = new int[comboLen];

			int combosComplete = 0;

			for (int m = 0; m < immNumCombos; m++) {
				String combo = "";

				for (int k = 0; k < comboLen; k++) {
					combo += Constants.getCodeElement(combos[i][k], indices[k]);
					int factor = (indices[k] + 1)
							* (int) Math.pow(2, comboLen - k - 1);

					if (m + 1 >= factor) {
						indices[k]++;
					}
				}

				combosComplete++;
				codes[i].add(combo);
			}
		}

		return codes;
	}

	private static int getNumCombos(VALID_T[] combos) {
		int len = combos.length;
		int numCombos = 1;

		for (int i = 0; i < len; i++) {
			numCombos *= Constants.getLength(combos[i]);
		}

		return numCombos;
	}

	public static String getRandomCode() {

		Constants.VALID_T[] type = Constants.COMBOS[VALID_CODE_INDEX
				% Constants.NUM_COMBO_TYPES];

		int len = type.length;

		String result = "";
		try {
			for (int i = 0; i < len; i++) {
				int timeout = (int) (System.currentTimeMillis() % 101);

				result += Constants.getRand(type[i]);
				if (i == len / 2)
					result += (VALID_CODE_INDEX + 1);

				// System.out.println(timeout+1);
				TimeUnit.MILLISECONDS.sleep(timeout + 1);
			}

			increaseValidCodeIndex();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
