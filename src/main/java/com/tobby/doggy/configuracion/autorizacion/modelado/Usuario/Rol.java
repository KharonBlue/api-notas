package com.tobby.doggy.configuracion.autorizacion.modelado.Usuario;

public enum Rol {
    USER, ADMIN;

    private static final String ROLE_PREFIJO = "ROL_";

    public String obtenerRol () {
        return ROLE_PREFIJO + this.name();
    }
}
