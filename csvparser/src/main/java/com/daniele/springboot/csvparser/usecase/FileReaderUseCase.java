package com.daniele.springboot.csvparser.usecase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daniele.springboot.csvparser.model.ParserConfigRequest;
import com.daniele.springboot.csvparser.utils.Constants;

public class FileReaderUseCase {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String pathIn;
	private String delimiter;
	
	public FileReaderUseCase(ParserConfigRequest config) {
		this.pathIn    = config.getInPath();
		this.delimiter = config.getDelimiter() != null ?  config.getDelimiter() : Constants.DEF_DELIMITER; 
	}
	
	public List<String[]> read() {
		logger.info("read begin, config={}, {}", pathIn, delimiter);
		
		List<String[]> lines = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(pathIn))){
			String line = "";
			while((line = br.readLine()) != null) 
			{
				String[] cols = line.split(delimiter);
				lines.add(cols);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return lines;
	}
	
	

}
