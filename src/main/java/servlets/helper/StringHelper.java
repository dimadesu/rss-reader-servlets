package servlets.helper;

public class StringHelper {

	public static String encode (String in) {
		// Replace single quotes with double single quotes. This syntax works for DB.
		// Selects will work fine and return only one quote without any extra effort
		String s = in.replaceAll("'", "''");
		return s.substring(0, Math.min(s.length(), 254));
	}
	
}
