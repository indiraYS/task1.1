package com.test.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;
/*
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
     /*   http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();*/
       http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")
                //.failureUrl("/login?error=true")
                .defaultSuccessUrl("/main")
               // .failureHandler(authenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /*@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
        *//*auth.inMemoryAuthentication()
                .withUser("indira")
                .password(passwordEncoder().encode("indira"))
                .roles("USER");*//*
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select USERNAME, 'USER' as ROLE from EMPLOYEE where USERNAME=?")
                .usersByUsernameQuery("select USERNAME, PASSWORD, 1 as enabled  from EMPLOYEE where USERNAME=?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
