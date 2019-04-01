package com.daniele.springboot.csvparser.usecase;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daniele.springboot.csvparser.model.ParserConfigRequest;

public class FilterDataUseCase {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String LOW_DECISION_HEADER = "0";
	private final String UP_DECISION_HEADER  = "1";
	
	public ParserConfigRequest config; 
	
	private Boolean hasHeader;
	
	public FilterDataUseCase(ParserConfigRequest config) {
		this.hasHeader = config.getHasHeader();
	}
	
	public List<String[]> filterData(List<String[]> data){
		logger.info("filterData  begins, data size : {}", data.size() + " rows");
		
		List<String[]> dataByUpDecision = filterByDecision(data, UP_DECISION_HEADER);
		logger.info("After filtering data by decision=1, new data size : {} rows", dataByUpDecision.size());
		Integer decisionIndex = data.get(0).length-1;
		
		Iterator<String[]> it = data.iterator();
		//skip first line if header exists
		if(hasHeader) 
		{
			 it.next();
		}
		 
		rows_loop:
		while(it.hasNext()) 
		{
			String[] row = it.next();
		    boolean isRemovable = false;
		    iVar_loop:
		    for(int i=1; i < decisionIndex; i++) 
		    {
				Integer iVar    = Integer.parseInt(row[i]);
				Integer iVarMin = getVarMin(dataByUpDecision, i);
				Integer iVarMax = getVarMax(dataByUpDecision, i);
				String decision = row[decisionIndex];
				logger.info("Var{}={} : max={}, min={}, decision={} ", i, iVar, iVarMax, iVarMin, decision);

				//Row validation for removal conditions
				if(LOW_DECISION_HEADER.equals(decision) 
					&& ( iVar > iVarMax || iVar < iVarMin )) 
				{
					isRemovable = true;
				}else {
					isRemovable = false;
					break iVar_loop;
				}
				logger.info("Var{}={},isRemovable = {}",i, iVar, isRemovable);	
			}
			
			if(isRemovable) 
			{
				it.remove();
				logger.info("Removed row");
			}
		}
        return data;
	}
	
	private List<String[]> filterByDecision(List<String[]> data, String decision) {
		logger.info("filterByDecision  begins, data.size={} rows, decision={}", data.size() , decision);
		
		return data.stream()
				.filter( r ->  decision.equals(r[r.length-1]))
				.collect(Collectors.toList()); 	
	}
	
	private int getVarMax(List<String[]> data, int n) {
		logger.info("getVarMax, data size={}", data.size() + " rows");
		final int index = n;
		Comparator<String[]> varNComparator = (a1, a2) -> Integer.valueOf(a1[index]) - Integer.valueOf(a2[index]);
		
		String [] rowOfVarNMax = 
				data.stream()
					.collect(Collectors.maxBy(varNComparator)).get();
		return Integer.parseInt(rowOfVarNMax[n]);
	}
	
	private int getVarMin(List<String[]> data, int n) {
		logger.info("getVarMin, data size={}", data.size() + " rows");
		final int index = n;
		Comparator<String[]> varNComparator = (a1, a2) -> Integer.valueOf(a1[index]) - Integer.valueOf(a2[index]);
		
		String [] rowOfVarNMax = 
				data.stream()
					.collect(Collectors.minBy(varNComparator)).get();
		return Integer.parseInt(rowOfVarNMax[n]);
	}

	
}
