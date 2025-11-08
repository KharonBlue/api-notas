package com.tobby.doggy.modelado.entidades.enumerados;

public enum NombreMateria {

    LENGUA("LENGUA"), MATEMATICAS("MATEMATICAS"),
    HISTORIA("HISTORIA"), NATURALEZA("NATURALEZA"),
    ED_FISICA("ED_FISICA"), ARTES("ARTES");

    private final String nombreMateria;

    NombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreMateria() {
        return this.nombreMateria;
    }
}
