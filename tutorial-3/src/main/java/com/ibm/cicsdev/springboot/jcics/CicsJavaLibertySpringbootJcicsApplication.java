package com.ibm.cicsdev.springboot.jcics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CicsJavaLibertySpringbootJcicsApplication {

	/** Name of the TSQ used throughout this Sample */
	public static final String TSQNAME = "EXAMPLE";
	
	public static void main(String[] args) {
		SpringApplication.run(CicsJavaLibertySpringbootJcicsApplication.class, args);
	}

}
