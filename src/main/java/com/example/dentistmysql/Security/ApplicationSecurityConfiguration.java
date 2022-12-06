package com.example.dentistmysql.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static String user;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select Username,Password,enabled from users where Username=?")
                .authoritiesByUsernameQuery("select Username, Role from users where Username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/*").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/dentist/*").hasAnyAuthority("ROLE_DENTIST")
                .antMatchers("/technician/*").hasAnyAuthority("ROLE_TECHNICIAN")
                .antMatchers("/","/**","/h2-console/**", "/static/css/*","/js/*")
                .permitAll().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .successHandler(loginSuccessHandler)
                .and().headers().frameOptions().sameOrigin().and().logout().logoutUrl("/logout");

    }
    public String currentUser(Authentication authentication){

        System.out.println(authentication.getName());

        return  user = authentication.getName();
    }
    public String getUserName() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isEmpty(username)) {
            username = "";
        }
        return username;
    }
}

