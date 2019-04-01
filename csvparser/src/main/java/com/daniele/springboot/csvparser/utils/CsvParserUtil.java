package com.daniele.springboot.csvparser.utils;

public class CsvParserUtil {
	
	public static String toCSV(String[] array, String delimiter) { 
		String result = ""; 
		if (array.length > 0) 
		{ StringBuilder sb = new StringBuilder(); 
			for (String s : array) { 
				sb.append(s).append(delimiter); 
			} 
			result = sb.deleteCharAt(sb.length() - 1).toString(); 
		} return result; 
	}

}
