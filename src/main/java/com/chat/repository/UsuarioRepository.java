package com.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
		Usuario findByUsername(String username);
		@Override
		List<Usuario> findAll();
		Usuario findRoleByUsername ( String username);
}
