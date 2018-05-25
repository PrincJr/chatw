package com.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.model.Funcao;

public interface FuncaoRepository extends MongoRepository<Funcao,String>{

}
