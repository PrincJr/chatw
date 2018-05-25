package com.chat.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.chat.model.Mensage;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<Mensage, String> {
    List<Mensage> findAllByOrderByDataAsc();
    
    @Query("{'author' : ?0 , 'recebedor' : ?1}")
    List<Mensage> findByAuthorAndRecebedor(String author , String recebedor);
    
    
}

