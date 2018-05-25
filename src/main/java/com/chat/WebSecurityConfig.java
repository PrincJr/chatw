package com.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.chat.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http
         .authorizeRequests()
             .antMatchers( "/js/**",
                     "/css/**",
                     "/img/**",
                     "/musica/**",
                     "/webjars/**").permitAll()
             .antMatchers("/**").authenticated()
         .and()
         .formLogin()
             .loginPage("/login")
             .defaultSuccessUrl("/")
             .permitAll()
             .and()
             .logout()
             .logoutRequestMatcher(
                     new AntPathRequestMatcher("/login?logout")
             ).logoutSuccessUrl("/login").permitAll()
         .and()
         .csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	 @Bean
		public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

	 @Bean
	    public UserDetailsService mongoUserDetails() {
	        return new CustomUserDetailsService();
	    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 UserDetailsService userDetailsService = mongoUserDetails();
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
