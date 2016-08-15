package org.template.config;

import org.template.enums.RoleType;
import org.template.service.CustomPersistentTokenBasedRememberMeServices;
import org.template.service.CustomUserDetailsService;
import org.template.service.PersistentLoginService;
import org.template.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final int TOKEN_VALIDITY_SECONDS = 86400;

    @Autowired
    private PersistentLoginService persistentLoginService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CustomPersistentTokenBasedRememberMeServices rememberMeServices() {
        String key = UUID.randomUUID().toString();
        return new CustomPersistentTokenBasedRememberMeServices(key, userDetailsService(), persistentLoginService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(StandardCharsets.UTF_8.displayName());
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.authorizeRequests()
                .antMatchers(Constants.Url.RESOURCES + "/**").permitAll()
                .antMatchers(Constants.Url.ROOT).permitAll()
                .antMatchers(Constants.Url.LOGIN + "/**").permitAll()
                .antMatchers(Constants.Url.ADMIN + "/**").hasAnyAuthority(RoleType.ADMIN.getName())
                .antMatchers(Constants.Url.USER + "/**").hasAnyAuthority(RoleType.ADMIN.getName(), RoleType.USER.getName())
                .and().formLogin().defaultSuccessUrl(Constants.Url.ROOT).loginPage(Constants.Url.LOGIN).failureUrl(Constants.Url.LOGIN + "?error=true")
                .and().rememberMe().rememberMeServices(rememberMeServices()).key(rememberMeServices().getKey()).tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and().logout().permitAll().logoutSuccessUrl(Constants.Url.LOGIN + "?logout=true")
                .and().exceptionHandling().accessDeniedPage(Constants.Url.ERROR_403)
                .and().csrf()
                .and()
                .userDetailsService(userDetailsService());
    }
}