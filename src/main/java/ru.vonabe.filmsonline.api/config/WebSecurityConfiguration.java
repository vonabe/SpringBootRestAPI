package ru.vonabe.filmsonline.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.vonabe.filmsonline.api.controller.AuthTokenFilter;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;
import ru.vonabe.filmsonline.api.exceptions.LoggingAccessDeniedHandler;
import ru.vonabe.filmsonline.api.repository.UserRepository;

import java.time.LocalDateTime;

@EnableOAuth2Sso
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthEntryPoint unauthorizedHandler;
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("id");
            UserEntity user = userRepository.findById(id).orElseGet(() -> {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(id);
                userEntity.setEmail((String) map.get("email"));
                userEntity.setVerified_email((Boolean) map.get("verified_email"));
                userEntity.setName((String) map.get("name"));
                userEntity.setGiven_name((String) map.get("given_name"));
                userEntity.setFamily_name((String) map.get("family_name"));
                userEntity.setPicture((String) map.get("picture"));
                userEntity.setLocale((String) map.get("locale"));
                return userEntity;
            });
            user.setLast_visit(LocalDateTime.now());
            return userRepository.save(user);
        };
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.NEVER ).and()
                .antMatcher("/**")
                .authorizeRequests().antMatchers( "/", "/index.html", "/auth/time", "/ws/**", "onlineintercativefilms-1.0-SNAPSHOT.jar").permitAll()
                .anyRequest().authenticated().and().logout().clearAuthentication(true);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
