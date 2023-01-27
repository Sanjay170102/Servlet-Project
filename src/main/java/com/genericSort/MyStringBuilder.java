package com.genericSort;

public class MyStringBuilder {
	public static String convertObjectArrayToString(Object[] arr, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr)
			sb.append(obj.toString()).append(delimiter);
		return sb.substring(0, sb.length() - 1);
	}
}
