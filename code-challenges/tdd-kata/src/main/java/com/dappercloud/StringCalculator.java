package com.dappercloud;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

	private static final String BASE_DELIMITER_GROUP = "[\\n,]";

	private static final String DELIMITER_INDICATOR = "//";

	private static final String DELIMITER_GROUP_START = "[";
	private static final String DELIMITER_GROUP_END = "]";

	private static final String DELIMITER_END = "\n";

	private static final int ADD_LIMIT = 1000;

	/**
	 * @param input - String separated by commas
	 * @return value of the separated string.
	 * 
	 *         The method can take up to two numbers, separated by commas, and will
	 *         return their sum. for example “” or “1” or “1,2” as inputs. (for an
	 *         empty string it will return 0)
	 * 
	 * @throws Exception - negative numbers are not allowed
	 */
	public static int addString(String input) throws Exception {

		int result = 0;
		List<Integer> negatives = new ArrayList<Integer>();

		String delimiter = getDelimiter(input);

		String values = input.startsWith(DELIMITER_INDICATOR) ? getValues(input) : input;

		for (String value : split(values, delimiter)) {
			int num = parse(value);
			if (num <= ADD_LIMIT) {
				result += num;
			}
			if (num < 0) {
				negatives.add(num);
			}
		}
		if (negatives.size() > 0) {
			String message = negatives.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "[", "]"));
			throw new Exception("Negatives not allowed " + message);
		}

		return result;
	}

	public static String[] split(String input, String delimiter) {
		return input.split(delimiter);
	}

	public static String getDelimiter(String input) {
		String delimiter = "";
		if (input.startsWith(DELIMITER_INDICATOR)) {
			Pattern regex = Pattern.compile("\\"+ DELIMITER_GROUP_START +"(.*?)\\"+ DELIMITER_GROUP_END);
			Matcher matcher = regex.matcher(input);
			while (matcher.find()) {// Finds Matching Pattern in String
				delimiter += "|\\Q" + matcher.group(1) + "\\E";
			}
		}
		return BASE_DELIMITER_GROUP + delimiter;
	}

	public static int parse(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			// Just catching
		}

		return result;
	}

	public static String getValues(String input) {
		//Not a huge fan but allows for the use of \n as a delimiter
		return input.substring(input.indexOf(DELIMITER_GROUP_END + DELIMITER_END) + DELIMITER_GROUP_END.length()
				+ DELIMITER_END.length());
	}

}
