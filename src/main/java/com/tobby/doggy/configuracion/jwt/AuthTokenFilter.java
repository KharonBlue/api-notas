package com.tobby.doggy.configuracion.jwt;

import com.tobby.doggy.configuracion.autorizacion.servicios.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    public static final String BEARER_ = "Bearer ";
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher(); //
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/docs/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/autorizar/**"
    );


    public AuthTokenFilter(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String path = request.getRequestURI();

            boolean isExcluded = EXCLUDED_PATHS.stream()
                    .anyMatch(pattern -> pathMatcher.match(pattern, path));

            if (isExcluded) {
                filterChain.doFilter(request, response);
                return;
            } else {

                String jwt = parseJwt(request);
                if (jwt != null && jwtUtils.validarJwtToken(jwt)) {
                    final String userName = jwtUtils.obtenerUsuarioDelToken(jwt);
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));
                    SecurityContextHolder.getContext()
                            .setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            log.error("No se pudo setear la autenticacion del usuario: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith(BEARER_)) {
            return headerAuth.substring(BEARER_.length());
        }
        return null;
    }


}
