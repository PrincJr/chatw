package com.chat;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@SpringBootApplication
public class ChatApplication {
	
	 @PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("AMT"));
	  }
	 
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(ChatApplication.class, args);
	
	}
	
	@Bean
	public DataSource dataSource() {
	    
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://35.188.169.198:3306/chatw?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=America/Sao_Paulo ");

		dataSource.setUsername("root");
		dataSource.setPassword("admin@9988");
		return dataSource;
	}
	
	//@Bean
	//public LayoutDialect layoutDialect() {
	//   return new LayoutDialect();
	//}
}
