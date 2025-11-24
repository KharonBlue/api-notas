package com.tobby.doggy.configuracion.autorizacion.modelado.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TokenRespuesta {
    String token;

    public TokenRespuesta(String token){
        this.token = token;
    }
}
