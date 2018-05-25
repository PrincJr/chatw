package com.chat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.Funcao;
import com.chat.model.Usuario;
import com.chat.repository.FuncaoRepository;
import com.chat.repository.UsuarioRepository;

@Controller
public class CadastroUsuarioController {
	
	
	@Autowired UsuarioRepository usuarioRepository;
	@Autowired FuncaoRepository funcaoRepository;
	
	@RequestMapping(value = "cadastrar-usuario" , method = RequestMethod.GET)
	public ModelAndView index() {
		String usuarioLogado = new String("");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = authentication.getName().toString();
			}
		}
		ModelAndView andView = new ModelAndView("cadastro-usuario");
		andView.addObject("user", usuarioLogado);
		List<Funcao> funcoes = (List<Funcao>) funcaoRepository.findAll();
		andView.addObject("funcoes", funcoes);
		return andView;
	}
	@RequestMapping(value = "cadastrar-usuario" , method = RequestMethod.POST)
	public String cadastrar(String username , String password , String authorities , String funcao ) {

		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		Usuario  user = new Usuario(username ,  hashedPassword ,funcao ,authorities);
		
		usuarioRepository.save(user);
		
		return "redirect:/cadastrar-usuario";
	}
}
