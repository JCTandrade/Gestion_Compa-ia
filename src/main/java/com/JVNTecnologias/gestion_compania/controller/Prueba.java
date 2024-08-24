package com.JVNTecnologias.gestion_compania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class Prueba {

    @GetMapping("/usuarios")
    @Operation(summary = "Obtener lista de usuarios", description = "Devuelve una lista de usuarios disponibles en el sistema")
    public String obtenerUsuarios(@RequestParam(value = "nombre", required = false) String nombre) {
        return "nombre por parametro" + nombre;
    }
}
