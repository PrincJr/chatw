package com.chat.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chat.model.Usuario;
import com.chat.repository.UsuarioRepository;

@Controller
public class DefaultController {

	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/login")
	public String login() {
	
		String password = "123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		String authorities = "ADMIN";
		Usuario user = new Usuario("Junior", hashedPassword, "Desenvolvedor", authorities );
		usuarioRepository.save(user);

		return "login";
	}
	@GetMapping("/layout")
	public String layout() {
		return "teste";
	}

}
