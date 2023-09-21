package com.javahelps.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.javahelps.restservice.entity.Authorise;
import com.javahelps.restservice.repository.AuthoriseRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthoriseRepository authoriseRepository;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//        	Authorise authorise = authoriseRepository.findOne(username);
//            if (authorise != null) {
//                return new org.springframework.security.core.userdetails.User(authorise.getUsername(), authorise.getPassword(),
//                        true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
//            } else {
//                throw new UsernameNotFoundException("Could not find the user '" + username + "'");
//            }
//        });
    	
    	auth.inMemoryAuthentication().withUser("swati").password("swati").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().csrf().disable();
//    	http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and().csrf().disable();
    	
    	http.cors().and().authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
    	
//.authenticationEntryPoint(new RestAuthenticationEntryPoint());
//http
//        .csrf().disable()
//        .headers()
//        .frameOptions().disable();


    }
}