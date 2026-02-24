package com.tobby.doggy.unitarios;

import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.IUsuarioRepositorio;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicioTest {

    @Mock
    private IUsuarioRepositorio usuarioRepositorio;


}
