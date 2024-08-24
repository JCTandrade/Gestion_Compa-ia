package com.JVNTecnologias.gestion_compania.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseGenerico {
    private HttpStatus status;
    private String message;
    private Object data;
}
