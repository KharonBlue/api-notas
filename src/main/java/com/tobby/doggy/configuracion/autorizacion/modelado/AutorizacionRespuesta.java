package com.tobby.doggy.configuracion.autorizacion.modelado;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AutorizacionRespuesta {
    String token;

    public AutorizacionRespuesta (String token){
        this.token = token;
    }
}
