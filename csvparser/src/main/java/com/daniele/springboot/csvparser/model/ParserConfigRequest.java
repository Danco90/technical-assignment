package com.daniele.springboot.csvparser.model;

import javax.validation.constraints.NotNull;

public class ParserConfigRequest {
	
	@NotNull
	private String inPath;
	
	private String delimiter;
	
	@NotNull
	private Boolean hasHeader;
	
	private String outPath;
	
	public ParserConfigRequest() {
		
	}
	
	public ParserConfigRequest(String inPath, Boolean hasHeader) {
		this.inPath = inPath;
		this.delimiter = ",";
		this.hasHeader = hasHeader;
		this.outPath = inPath.replaceAll("input", "output"); 
	}
	

	public ParserConfigRequest(String inPath, String delimiter, Boolean hasHeader, String outPath) {
		this.inPath = inPath;
		this.delimiter = delimiter.equals(null) ? "," : delimiter;
		this.hasHeader = hasHeader == null ? true : hasHeader;
		this.outPath = outPath.equals(null) ? inPath.replaceAll("input", "output") : outPath ;
	}

	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public Boolean getHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}
	
}
