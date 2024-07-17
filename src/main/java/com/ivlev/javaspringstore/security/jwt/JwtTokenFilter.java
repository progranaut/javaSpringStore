package com.ivlev.javaspringstore.security.jwt;

import com.ivlev.javaspringstore.security.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = getToken(request);
            if (jwtToken != null && jwtUtils.validate(jwtToken)) {
                String username = jwtUtils.getUsername(jwtToken);

                System.out.println(username);

                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                System.out.println(userDetails);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                System.out.println("test1");

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                System.out.println("test2");

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("test3");
            }
        } catch (Exception e) {
            log.error("Невозможно установить аутентификацию пользователя: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;

    }
}
