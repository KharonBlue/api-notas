package com.tobby.doggy.integracion.config;

import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.IUsuarioRepositorio;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Rol;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class TestConfiguracion {

    private final IUsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public TestRestTemplate testRestTemplate() {
        usuarioRepositorio.findByNombreUsuario("test@gmail.com").orElseGet(() -> {
            Usuario usuario = Usuario.builder()
                    .rol(Rol.USER)
                    .nombreUsuario("test@gmail.com")
                    .contrasenia(passwordEncoder.encode("12345678"))
                    .build();

            return usuarioRepositorio.save(usuario);
        });

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri("http://localhost:8080");

        return new TestRestTemplate(restTemplateBuilder);
    }
}
