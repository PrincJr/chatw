package com.chat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
	
	  public static final String USERNAME = "username";
	    public static final String PASSWORD = "password";
	    public static final String ROLES = "roles";

	    @JsonProperty(USERNAME)
	    private String username;

	    @JsonProperty(PASSWORD)
	    private String password;
	    
	    private String funcao;
	    
	    @JsonProperty(ROLES)
	    private String roles ;
	    
	    public Usuario(String username,String password  ,String funcao ,  String roles) {
	        this.username = username;
	        this.password = password;
	       this.funcao = funcao;
	       this.roles = roles;
	    }

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFuncao() {
			return funcao;
		}

		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

}
