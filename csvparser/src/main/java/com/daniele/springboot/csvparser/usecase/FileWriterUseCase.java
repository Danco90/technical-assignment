package com.daniele.springboot.csvparser.usecase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daniele.springboot.csvparser.model.ParserConfigRequest;
import com.daniele.springboot.csvparser.utils.Constants;
import com.daniele.springboot.csvparser.utils.CsvParserUtil;

public class FileWriterUseCase {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String pathOut;
	private String delimiter;
	
	public FileWriterUseCase(ParserConfigRequest config) {
		
		String defaultOut = config.getInPath()
				.replaceAll(Constants.IN_DIR, Constants.OUT_DIR); 
		this.pathOut   = config.getOutPath() != null ? config.getOutPath() : defaultOut ;
		this.delimiter = config.getDelimiter() != null ? config.getDelimiter() : Constants.DEF_DELIMITER;
	}
	
	public void write(List<String[]> lines) {
		logger.info("write  begins");
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(pathOut))){
			for(String [] line : lines) {
				String arrTostr = CsvParserUtil.toCSV(line, delimiter);
				logger.info("writing to CSV  line='{}'", arrTostr);
		    		writer.write(arrTostr+"\n");
			}
		}
		catch (IOException ex) {
				ex.printStackTrace();
		}
	}
	
	public void write(String line) {
		logger.info("write to CSV  str= '{}'", line);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(pathOut))){
			writer.write(line);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

}
