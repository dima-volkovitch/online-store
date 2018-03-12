package by.grsu.edu.dimav.onlinestore.utils;

public class StringWorker {
	public static String uniteStrings(Object...strings) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < strings.length; i++) {
			str.append(isNull(strings[i]));
		}
		return str.toString();
	}
	
	private static String isNull(Object str) {
		if(str == null) {
			return "null";
		}
		return str.toString();
	}
}
