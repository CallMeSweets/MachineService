package com.configuration.machine.security.filters;

import com.configuration.machine.security.models.User;
import com.configuration.machine.security.service.JWTService;
import com.configuration.machine.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("start filtering user request");
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            log.trace("JWT Token: " + jwtToken);
            username = jwtService.extractUsername(jwtToken);
            log.trace("Username: ", username);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = this.userService.loadUserByUsername(username);
            log.trace("User founded, username: ", username);
            if(jwtService.validateToken(jwtToken, user)){
                log.trace("token is valid");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                log.trace("UsernamePasswordAuthenticationToken created");
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.trace("UsernamePasswordAuthenticationToken set as context");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
