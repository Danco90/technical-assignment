# csv parser 

This repository contains the sample code for the following techical exercise :

Make a simple web interface where users can upload a CSV file consisting of the following columns :
 
	● Id (unique int identifiers); 
	● Decision consisting of either a 0 or 1
	● an arbitrary number of &quot;variable&quot; columns consisting of numeric data.

The app should parse the csv file and remove any records (rows) which meet both of the
following conditions:

	● Have a Decision of 0
	● For each variable (column), no value falls between FMIN and FMAX.
	Where FMIN and FMAX are the smallest and largest value for that variable across all records
	with a decision value of 1.

This new stripped down data should then be shown to the user in tabular format.

csv-parser API Overview

Basically, the API exposes a service which aims to parse a file.csv and return its modified data. The file is being imported by specifying a set of input parameter, such as its in/out paths. 

Overall, the process consists of importing the file content in a java data structure (List of Strings Array) and determining if each item has to be deleted or not, according to the condition as per requirements. The aproach results in a new data structure filtered by condition, whose items are meant to be written as line in a new file.
 
Open the command prompt/Terminal 
to run the code clone the repository using below command - 

git clone https://github.com/Danco90/technical-exercise.git

## compile the code-
	mvn clean install

## run the compiled code -
	mvn spring-boot:run

## unit test ( coming soon)
	mvn test

## test BE API with a REST Client (Postman, Advance REST Client, etc) 

1.Test the Application by Type the following request 

	POST http://localhost:8080/api/csv-parser/config
	Content type application/json
	
	{
	 "inPath":"input/exampleA_input.csv",
	 "delimiter":",",
	 "hasHeader":true,
	 "outPath":"output/exampleA_output.csv" 
	}

You're supposed to get the following response 

	201 Created
	{
	"destination": "output/exampleA_output.csv",
	"numberOfRecords": 4,
	"numDeletions": 2
	}

##Remaining task

2.Define UI (src/main/resources/static/ )in order to show the data in tabular format.
the interface will need to perform the following action :

	●  2.1. allowing the user to upload a file.csv , by specifing at least the output file path
	●  2.2. connecting to the endpoint "http://localhost:8088/api/csv-parser/config" exposed by the backend API , by passing the 	right configuration for IN/OUT file path, row delimiter and hasHeader flag.
	
	●  2.3. show the new file.csv up-to-date 

## Future improvements

	● Testing Automation
	● Cloud enabling over microservice architecture : Split Monolithic service in more micro-services with Proxy client, 		loadbalancer, NamingServer, CloudConfigServer and so on.
