package com.daniele.springboot.csvparser.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.springboot.csvparser.exception.CsvParserConfigurationException;
import com.daniele.springboot.csvparser.model.ParserResponse;
import com.daniele.springboot.csvparser.model.ParserConfigRequest;
import com.daniele.springboot.csvparser.model.mapper.Mapper;
import com.daniele.springboot.csvparser.usecase.FileReaderUseCase;
import com.daniele.springboot.csvparser.usecase.FileWriterUseCase;
import com.daniele.springboot.csvparser.usecase.FilterDataUseCase;

@Service
public class CsvParserServiceImpl implements CsvParserService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private FileReaderUseCase readerUseCase;
	
	private FilterDataUseCase filterDataUseCase;
	
	private FileWriterUseCase writerUseCase;
	
	@Override
	public ParserResponse parse(ParserConfigRequest input) throws FileNotFoundException, IOException, CsvParserConfigurationException {
		logger.info("parse  begins");
		readerUseCase = new FileReaderUseCase(input);
		
		//READING DATA
		List<String[]> data = readerUseCase.read();
		int dataSize = data.size();
		if(dataSize == 0) {
			throw new CsvParserConfigurationException("Wrong input : empty file or bad parameters)");
		}
		
		logger.info("data read : {} rows found. hasHeader : {}", dataSize, input.getHasHeader());
		
		//Header information 
        String[] headers = {};
        if(input.getHasHeader()){
            headers = data.get(0);
            logger.info("Avaliable headers: " + Arrays.toString(headers));
        }
        
        //FILTERING DATA
        filterDataUseCase = new FilterDataUseCase(input);
        
        List<String[]> filteredData = filterDataUseCase.filterData(data);
        int numDeletions = dataSize - filteredData.size();
        logger.info("numDeletedLines: " + numDeletions);
        
        //WRITING DATA
        writerUseCase = new FileWriterUseCase(input);
        writerUseCase.write(filteredData);
        
        return Mapper.mapToResponse(input.getOutPath(), filteredData.size(), numDeletions);
	}

}
