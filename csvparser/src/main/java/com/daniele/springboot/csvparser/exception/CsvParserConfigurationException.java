package com.daniele.springboot.csvparser.exception;

import java.io.IOException;

public class CsvParserConfigurationException extends IOException {
	
	private static final long serialVersionUID = -3332292346834265371L;
	
	public CsvParserConfigurationException(String mex) {
		super("CsvPathConfigurationException. " + mex);
	}
}
