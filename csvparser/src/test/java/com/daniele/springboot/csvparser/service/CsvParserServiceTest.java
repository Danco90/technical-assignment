package com.daniele.springboot.csvparser.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daniele.springboot.csvparser.model.ParserResponse;
import com.daniele.springboot.csvparser.model.ParserConfigRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvParserServiceTest {
	
	@Autowired
	CsvParserService service;
	
	@Test
	public void parse () throws IOException {
		
		final String EXAMPLE_A_INPUT = "input/exampleA_input.csv";
		final String EXAMPLE_A_OUTPUT = "output/exampleA_output.csv";
		ParserConfigRequest configA = new ParserConfigRequest(EXAMPLE_A_INPUT, ",", true, EXAMPLE_A_OUTPUT);
		
		ParserResponse responseA = service.parse(configA);
		int resultedRecordExampleA = responseA.getNumberOfRecords();
		assertEquals(4, resultedRecordExampleA);

		final String EXAMPLE_B_INPUT = "input/exampleB_input.csv";
		final String EXAMPLE_B_OUTPUT = "output/exampleB_output.csv";
		ParserConfigRequest configB = new ParserConfigRequest(EXAMPLE_B_INPUT, ",", true, EXAMPLE_B_OUTPUT);
		
		ParserResponse responseB = service.parse(configB);
		int resultedRecordExampleB = responseB.getNumberOfRecords();
		
		assertEquals(981, resultedRecordExampleB);
		
		final String EXAMPLE_C_INPUT = "input/exampleC_input.csv";
		final String EXAMPLE_C_OUTPUT = "output/exampleC_output.csv";
		ParserConfigRequest configC = new ParserConfigRequest(EXAMPLE_C_INPUT, ",", true, EXAMPLE_C_OUTPUT);
		
		ParserResponse responseC = service.parse(configC);
		int resultedRecordExampleC = responseC.getNumberOfRecords();
		
		assertEquals(7, resultedRecordExampleC);
		
	}

}
