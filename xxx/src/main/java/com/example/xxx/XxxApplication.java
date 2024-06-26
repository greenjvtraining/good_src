package com.example.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class XxxApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxxApplication.class, args);
	}

}
