package com.chat.model;

import org.springframework.data.annotation.Id;

public class Funcao {

	@Id private String id;
	private String funcao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
}
