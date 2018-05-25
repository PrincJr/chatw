package com.chat.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chat.model.MongoUserDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoUserDetailService implements UserDetailsService{

	  @Autowired
	    private MongoClient mongoClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 MongoDatabase database = mongoClient.getDatabase("teste");
	        MongoCollection<Document> collection = database.getCollection("usuario");
	 
	        Document document = collection.find(Filters.eq("username",username)).first();
	 
	        if(document!=null) {
	 
	            String password = document.getString("password");
	            List<String> authorities = (List<String>) document.get("authorities");
	 
	            MongoUserDetails mongoUserDetails = new MongoUserDetails(username,password,authorities.toArray(new String[authorities.size()]));
	 
	            return mongoUserDetails;
	        } else {
	 
	           throw new UsernameNotFoundException("username not found");
	        }
	}

}
