package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// this is added to branch 1
//123
//this comment is added by branch2
@SpringBootApplication(scanBasePackages = "com.nt.controller")
public class OnlineExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}

}
