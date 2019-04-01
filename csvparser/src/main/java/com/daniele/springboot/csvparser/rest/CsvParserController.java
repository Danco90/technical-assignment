package com.daniele.springboot.csvparser.rest;

import java.io.IOException;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniele.springboot.csvparser.exception.CsvParserConfigurationException;
import com.daniele.springboot.csvparser.model.ParserResponse;
import com.daniele.springboot.csvparser.model.ParserConfigRequest;
import com.daniele.springboot.csvparser.service.CsvParserService;

@RequestMapping(CsvParserEndpoint.ROOT)
@RestController
public class CsvParserController implements CsvParserEndpoint {
	
	@Autowired
	CsvParserService service;

	@PostMapping("/config")
	public ResponseEntity<ParserResponse> parseFile(@Valid @RequestBody ParserConfigRequest input) throws CsvParserConfigurationException, IOException {
		
		ParserResponse response = service.parse(input);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{outPath}")
				.buildAndExpand(response).toUri();

		return ResponseEntity
				.created(location)
				.body(response);
	}

}
