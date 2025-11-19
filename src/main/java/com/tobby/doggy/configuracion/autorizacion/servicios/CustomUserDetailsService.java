package com.tobby.doggy.configuracion.autorizacion.servicios;

import com.tobby.doggy.configuracion.autorizacion.modelado.Usuario.IUsuarioRepositorio;
import com.tobby.doggy.configuracion.autorizacion.modelado.Usuario.Usuario;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepositorio.findByNombreUsuario(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario " + username + " no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(
                usuario.get().getUsername(),
                usuario.get().getPassword(),
                usuario.get().getAuthorities()
        );
    }


}
