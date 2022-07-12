package com.techprimers.mongodb.springbootmongodbexample.security;

import com.techprimers.mongodb.springbootmongodbexample.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index")
                .permitAll()
                .antMatchers("/rest/**").hasRole(ApplicationUserRoles.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dat= User.builder().username("dat")
                .password(this.passwordEncoder().encode("25"))
                .roles(Role.ADMIN.name()).build();

        UserDetails huyentran= User.builder().username("huyentran")
                .password(this.passwordEncoder().encode("23"))
                .roles(Role.USER.name()).build();

//        UserDetails tam= User.builder().username("huyentran")
//                .password(this.passwordEncoder().encode("23"))
//                .roles(ApplicationUserRoles.HAFT_ADMIN.name()).build();

        return new InMemoryUserDetailsManager(dat,huyentran);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
