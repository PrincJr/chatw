package com.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.Funcao;
import com.chat.repository.FuncaoRepository;

@Controller
public class CadastroFuncaoController {

	@Autowired FuncaoRepository funcaoRepository;
	
	@RequestMapping(value = "cadastrar-funcao"  , method = RequestMethod.GET)
	public ModelAndView index() {
		String usuarioLogado = new String("");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = authentication.getName().toString();
			}
		}
		ModelAndView andView = new ModelAndView("cadastrar-funcao");
		andView.addObject("user", usuarioLogado);
		return andView;
	}
	
	@RequestMapping(value = "cadastrar-funcao"  , method = RequestMethod.POST)
	public ModelAndView funcaoSalva(String funcao) {
		Funcao funcao2 = new Funcao(funcao);
		funcaoRepository.save(funcao2);
		ModelAndView andView = new ModelAndView("redirect:/cadastrar-funcao");
	
		return andView;
	}
}
