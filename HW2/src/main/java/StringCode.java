import java.util.HashSet;
import java.util.Set;
import java.lang.*;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if (str.length() < 1) return 0;
		int maxlen = 1;
		int current = 1;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) != str.charAt(i - 1)) {
				maxlen = Math.max(maxlen, current);
				current = 1;
			} else {
				current = current + 1;
			}
		}
		maxlen = Math.max(maxlen, current);
		return maxlen; // YOUR CODE HERE
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		String return_val = "";
		if (str.length() < 1) {
		    return return_val;
		}
		for (int i = 0; i < str.length() - 1; i++) {
			if (Character.isDigit(str.charAt(i))) {
				for (int j = 0; j < (int)(str.charAt(i) - '0'); j++) {
					return_val = return_val + str.substring(i + 1, i + 2);
				}
			} else {
				return_val = return_val + str.substring(i, i + 1);
			}
		}
		if (!Character.isDigit(str.charAt(str.length() - 1))) {
			return_val = return_val + str.substring(str.length() - 1, str.length());
		}
		return return_val; // YOUR CODE HERE
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		if (a.length() == b.length() && a.length() == len) return true;
		return false; // YOUR CODE HERE
	}
}
