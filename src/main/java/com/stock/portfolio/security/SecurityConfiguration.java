package com.stock.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/stock/user/**")
                .hasAuthority("ADMIN")
                .mvcMatchers("/stock/user/myself/**")
                .hasAuthority("USER")
                .mvcMatchers("/stock/portfolio/**")
                .hasAuthority("ADMIN")
                .mvcMatchers("/stock/portfolio/myself/**")
                .hasAuthority("USER")
                .mvcMatchers("/stock/position/**")
                .hasAuthority("ADMIN")
                .mvcMatchers("/stock/position/myself/**")
                .hasAuthority("USER")
                .mvcMatchers("**/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //.loginPage("/login") this is for custom login
                .permitAll()
                .and()
                .logout()
                .permitAll();

        // Look into csrf
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance(); }

}
