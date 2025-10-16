package com.tobby.doggy.controladores;

import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas/profesor")
public class ProfesorControlador {

    @PostMapping("/crear")
    public ProfesorRespuesta crear(@RequestBody ProfesorPeticion profesorPeticion) {


        return new ProfesorRespuesta();
    }

    @PutMapping("/actualizar/{id}")
    public HttpStatus actualizar(@PathVariable int id, @RequestBody ProfesorPeticion profesorPeticion) {

        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/listar")
    public List<ProfesorRespuesta> listar() {

        return List.of(new ProfesorRespuesta());
    }
}
