package com.daniele.springboot.csvparser.usecase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daniele.springboot.csvparser.model.ParserConfigRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterDataUseCaseUnitTest {
	
	private FilterDataUseCase filterDataUseCase;
	
	@Test
	public void filterData(){
		final String INPUT_FILE_PATH = "input/exampleA_input.csv";
		final String OUTPUT_DIR = "output/exampleA_output.csv";
		ParserConfigRequest config = new ParserConfigRequest(INPUT_FILE_PATH, ",", true, OUTPUT_DIR);
		
		String[] header = {"id", "Var1", "Decision"};
		String[] row1 = {"1", "10", "0"};
		String[] row2 = {"2", "20", "1"};
		String[] row3 = {"3", "30", "0"};
		String[] row4 = {"4", "40", "1"};
		String[] row5 = {"5", "50", "0"};
		
		List<String[]> data = new ArrayList<String[]>();
		data.add(header);
		data.add(row1);
		data.add(row2);
		data.add(row3);
		data.add(row4);
		data.add(row5);
		
		filterDataUseCase = new FilterDataUseCase(config);
		List<String[]> output = filterDataUseCase.filterData(data);
		assertEquals(4,output.size());
		
		final String INPUT_FILE_PATH_C = "input/exampleC_input.csv";
		final String OUTPUT_DIR_C = "output/exampleC_output.csv";
		ParserConfigRequest configC = new ParserConfigRequest(INPUT_FILE_PATH_C, ",", true, OUTPUT_DIR_C);
		
		
		String[] headerC = {"id", "Var1", "Var2", "Decision"};
		String[] rowC1 = {"1", "10", "9", "0"};
		String[] rowC2 = {"2", "20", "4", "1"};
		String[] rowC3 = {"3", "30", "1", "0"};
		String[] rowC4 = {"4", "40", "7", "1"};
		String[] rowC5 = {"5", "50", "5", "0"};
		String[] rowC6 = {"6", "20", "11", "0"};
		String[] rowC7 = {"7", "25", "6", "0"};
	
		List<String[]> dataC = new ArrayList<String[]>();
		dataC.add(headerC);
		dataC.add(rowC1);
		dataC.add(rowC2);
		dataC.add(rowC3);
		dataC.add(rowC4);
		dataC.add(rowC5);
		dataC.add(rowC6);
		dataC.add(rowC7);
		
		filterDataUseCase = new FilterDataUseCase(configC);
		List<String[]> outputC = filterDataUseCase.filterData(dataC);
		
		assertEquals(7,outputC.size());
		
	}
	

}
