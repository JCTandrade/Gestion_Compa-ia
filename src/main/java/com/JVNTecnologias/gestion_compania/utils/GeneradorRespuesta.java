package com.JVNTecnologias.gestion_compania.utils;

import com.JVNTecnologias.gestion_compania.Enum.EstadosEnum;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GeneradorRespuesta {
    public ResponseGenerico generarRespuesta(HttpStatus status, EstadosEnum estadosEnum, String message, Object data) {
        return new ResponseGenerico(status, estadosEnum, message, data);
    }
}
