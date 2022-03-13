package com.aexonics.userdetails.filter;

import com.aexonics.userdetails.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken;

        if(request.getRequestURI().contains("/api/v1/user-details") && !request.getMethod().equalsIgnoreCase("post")){
           String method = request.getMethod();
            final String requestTokenHeader = request.getHeader("Authorization");

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                if (jwtTokenUtil.validateToken(jwtToken)) {
                    filterChain.doFilter(request,response);
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

        }
        else {
            filterChain.doFilter(request,response);
        }
    }
}
