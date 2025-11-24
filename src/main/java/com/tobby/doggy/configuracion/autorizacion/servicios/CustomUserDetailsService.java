package com.tobby.doggy.configuracion.autorizacion.servicios;

import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.TokenRespuesta;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.IniciarSesionPeticion;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.RegistroPeticion;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.IUsuarioRepositorio;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Usuario;
import com.tobby.doggy.configuracion.autorizacion.servicios.mapeadores.UserDetailsMapeador;
import com.tobby.doggy.configuracion.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserDetailsMapeador mapeador;

    @Autowired
    public CustomUserDetailsService(IUsuarioRepositorio usuarioRepositorio,
                                    PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                                    UserDetailsMapeador mapeador) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.encoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.mapeador = mapeador;
    }

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

    public String registrar(RegistroPeticion registroPeticion) {
        if (usuarioRepositorio.findByNombreUsuario(registroPeticion.getNombreUsuario()).isPresent()) {
            throw new RuntimeException("Usuario ya existente");
        }
        usuarioRepositorio.save(mapeador.registrar(registroPeticion, encoder.encode(registroPeticion.getContrasenia())));
        return "Usuario registrado correctamente";
    }

    public TokenRespuesta iniciarSesion(IniciarSesionPeticion iniciarSesionPeticion, AuthenticationManager authenticationManager) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        iniciarSesionPeticion.getNombreUsuario(),
                        iniciarSesionPeticion.getContrasenia()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new TokenRespuesta(jwtUtils.generarToken(userDetails.getUsername()));
    }

}
