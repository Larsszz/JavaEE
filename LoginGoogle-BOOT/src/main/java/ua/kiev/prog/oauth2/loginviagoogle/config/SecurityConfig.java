package ua.kiev.prog.oauth2.loginviagoogle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth_login", "/js/**", "/css/**", "/favicon.ico", "/logout")
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .oauth2Login()
                    .loginPage("/oauth_login")
                    .successHandler(authenticationSuccessHandler)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }
}