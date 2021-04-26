package com.assessment.shapes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configurations for authentication and authorization of apis
 *
 * @author Jyoti
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	/**
	* Method overrides the configure method of WebSecurityConfigurerAdapter.
	* It is to authenticate the requests and allow some patterns on which the 
	* authentication is ignored.
	*
	* @param  http  HttpSecurity instance
	*/
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable().headers().disable()
         .authorizeRequests().antMatchers(
                 "/", "/csrf",
                 "/v2/api-docs", 
                 "/swagger-resources/**",
                 "/swagger-ui.html",
                 "/webjars/**",
                 "/addShape","/h2"
                 ).permitAll().anyRequest().authenticated()
         .and()
         .httpBasic();
    }
 
    /**
	* Method using in memory user credentials for authentication 
	*
	* @param  auth  AuthenticationManagerBuilder instance
	*/
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
    }
}
