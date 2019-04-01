package com.daniele.springboot.csvparser.rest;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.daniele.springboot.csvparser.exception.CsvParserConfigurationException;
import com.daniele.springboot.csvparser.model.ParserResponse;
import com.daniele.springboot.csvparser.model.ParserConfigRequest;

public interface CsvParserEndpoint {
	
	public final String ROOT = "/api/csv-parser";
	
	@PostMapping("/config")
	ResponseEntity<ParserResponse> parseFile(@RequestBody ParserConfigRequest input) throws CsvParserConfigurationException, IOException;
	
}
