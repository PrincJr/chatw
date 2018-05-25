package com.chat;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ChatApplication {
	
	 @PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("AMT"));
	  }
	 
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(ChatApplication.class, args);
	
	}
	
	//@Bean
	//public LayoutDialect layoutDialect() {
	//   return new LayoutDialect();
	//}
}
