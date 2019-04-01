package com.daniele.springboot.csvparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.daniele.springboot.csvparser",
		"com.daniele.springboot.csvparser.rest",
		"com.daniele.springboot.csvparser.service"})
public class CsvparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvparserApplication.class, args);
	}

}
