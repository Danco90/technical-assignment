package com.daniele.springboot.csvparser.model;

public class ParserResponse {
	
	private String  destination;
	private Integer numberOfRecords;
	private Integer numDeletions;
	
	public ParserResponse() {
		
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Integer getNumDeletions() {
		return numDeletions;
	}

	public void setNumDeletions(Integer numDeletions) {
		this.numDeletions = numDeletions;
	}
	
}
