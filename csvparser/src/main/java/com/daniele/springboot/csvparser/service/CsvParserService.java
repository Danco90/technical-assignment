package com.daniele.springboot.csvparser.service;

import java.io.IOException;

import com.daniele.springboot.csvparser.exception.CsvParserConfigurationException;
import com.daniele.springboot.csvparser.model.ParserResponse;
import com.daniele.springboot.csvparser.model.ParserConfigRequest;

public interface CsvParserService {
	
	ParserResponse parse(ParserConfigRequest input) throws CsvParserConfigurationException, IOException;
	
}
