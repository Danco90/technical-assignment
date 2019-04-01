package com.daniele.springboot.csvparser.model.mapper;

import com.daniele.springboot.csvparser.model.ParserResponse;

public class Mapper {
	
	public static ParserResponse mapToResponse(String path, int numRecords, int numDeletions) {
		ParserResponse out = new ParserResponse();
		out.setDestination(path);
		out.setNumberOfRecords(numRecords);
		out.setNumDeletions(numDeletions);
		
		return out;
	}

}
