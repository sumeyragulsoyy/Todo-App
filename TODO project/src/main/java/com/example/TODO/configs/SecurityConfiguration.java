package com.example.TODO.configs;

import com.example.TODO.repository.UserRepository;
import com.example.TODO.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    //@Override
    //protected void configure(HttpSecurity httpSecurity) throws  Exception{
    //    httpSecurity.authorizeRequests().antMatchers("/todolist")
    //            .permitAll().and()
    //            .authorizeRequests().antMatchers("/h2/**").permitAll();
//
    //    httpSecurity.csrf().disable();
    //    httpSecurity.headers().frameOptions().disable();
//
    //}

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                //.antMatchers("**/secure/**").authenticated()
                //.anyRequest().permitAll();
        .antMatchers("/h2/*").permitAll()
                .anyRequest().permitAll();   //authenticated().and().formLogin();
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return true;
                    }
                });
    }
}
