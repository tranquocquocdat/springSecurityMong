package com.techprimers.mongodb.springbootmongodbexample.security;

import com.techprimers.mongodb.springbootmongodbexample.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/","index")
                .permitAll()
                .antMatchers("/rest/**").hasRole(ApplicationUserRoles.ADMIN.name())
//                .antMatchers(HttpMethod.GET,"/rest/**").hasAuthority(ApplicationUserPermissions.COURSE_READ.getPermissions())
//                .antMatchers(HttpMethod.POST,"/rest/**").hasAuthority(ApplicationUserPermissions.COURSE_READ.getPermissions())
//                .antMatchers(HttpMethod.DELETE,"/rest/**").hasAuthority(ApplicationUserPermissions.STUDENT_WRITE.getPermissions())
//                .antMatchers(HttpMethod.POST,"/rest/**").hasAuthority(ApplicationUserPermissions.STUDENT_READ.getPermissions())
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
                .password(this.passwordEncoder().encode("dat"))
                .roles(ApplicationUserRoles.ADMIN.name()).build();

        UserDetails huyentran= User.builder().username("huyentran")
                .password(this.passwordEncoder().encode("huyentran"))
                .authorities(ApplicationUserRoles.HAFT_ADMIN.getGrantedAuthorities()).build();

        UserDetails tam= User.builder().username("tam")
                .password(this.passwordEncoder().encode("tam"))
                .authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).build();

        return new InMemoryUserDetailsManager(dat,huyentran,tam);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
