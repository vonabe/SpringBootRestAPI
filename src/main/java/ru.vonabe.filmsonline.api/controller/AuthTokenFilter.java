package ru.vonabe.filmsonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vonabe.filmsonline.api.entites.api.TokenEntity;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;
import ru.vonabe.filmsonline.api.services.TokenServices;
import ru.vonabe.filmsonline.api.services.UserServices;
import ru.vonabe.filmsonline.api.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServices tokenRepository;
    @Autowired
    private UserServices userRepository;

    private UserEntity getUserEntityByToken(String token) {
        if (token != null) {
//            System.out.println("TokenRepository - " + tokenRepository + " UserRepository - " + userRepository);
            TokenEntity tokenEntity = tokenRepository.findByToken(token);
            if (tokenEntity != null) {
                String userid = tokenEntity.getUserid();
                if (userid != null) {
                    return userRepository.findById(userid);
                }
            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = JwtTokenUtil.formatToken(request.getHeader("Authorization"));
        final UserEntity userEntity = getUserEntityByToken(token);

        if (userEntity != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getUsername(), null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

}
