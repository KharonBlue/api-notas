package com.tobby.doggy.controladores;

import com.tobby.doggy.configuracion.autorizacion.modelado.Usuario.IUsuarioRepositorio;
import com.tobby.doggy.configuracion.autorizacion.modelado.Usuario.Rol;
import com.tobby.doggy.configuracion.autorizacion.modelado.Usuario.Usuario;
import com.tobby.doggy.configuracion.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autorizar")
public class AuntenticacionControlador {

    private AuthenticationManager authenticationManager;
    private IUsuarioRepositorio usuarioRepositorio;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    @Autowired
    public AuntenticacionControlador(AuthenticationManager authenticationManager,
                                     IUsuarioRepositorio usuarioRepositorio,
                                     PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepositorio = usuarioRepositorio;
        this.encoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/registrar")
    public String registrar(@RequestBody Usuario usuario) {
        if(usuarioRepositorio.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()){
            return "Usuario ya existente";
        }
        final Usuario nuevoUsuario = new Usuario(
                null,
                usuario.getNombreUsuario(),
                encoder.encode(usuario.getContrasenia()),
                Rol.USER);

        usuarioRepositorio.save(nuevoUsuario);

        return "Usuario registrado correctamente";
    }

    @PostMapping(value = "/iniciar-sesion")
    public String iniciarSesion(@RequestBody Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        usuario.getNombreUsuario(),
                        usuario.getContrasenia()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generarToken(userDetails.getUsername());
    }
}
