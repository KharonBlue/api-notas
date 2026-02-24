package com.tobby.doggy.configuracion;

import com.tobby.doggy.configuracion.autorizacion.servicios.CustomUserDetailsService;
import com.tobby.doggy.configuracion.jwt.AuthEntryPointJwt;
import com.tobby.doggy.configuracion.jwt.AuthTokenFilter;
import com.tobby.doggy.configuracion.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authTokenFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    //@Profile("test")
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthTokenFilter authTokenFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // desactiva CSRF para pruebas
                .cors(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt))
                .sessionManagement(manejadorSesion ->
                        manejadorSesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //.anyRequest().permitAll() //Para desarrollar habilito esta configuracion
                        .requestMatchers(
                                "/autorizar/**",
                                "/docs/**"
                        ).permitAll() // rutas sin seguridad
                        .anyRequest().authenticated() // el resto requiere login*/
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
