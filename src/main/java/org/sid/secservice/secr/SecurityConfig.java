package org.sid.secservice.secr;

import org.sid.secservice.secr.fliters.JwtAuthenticationFilter;
import org.sid.secservice.secr.fliters.JwtAutorisationFilter;
import org.sid.secservice.secr.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImp  userDetailsService ;

    public SecurityConfig(UserDetailsServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() ;
        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS)) ;
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/h2-console/**" , "/refreshToken/**" , "/login").permitAll() ;
        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER")  ;
        // http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN") ;
        //http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean())) ;
        http.addFilterBefore(new JwtAutorisationFilter(), UsernamePasswordAuthenticationFilter.class) ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
