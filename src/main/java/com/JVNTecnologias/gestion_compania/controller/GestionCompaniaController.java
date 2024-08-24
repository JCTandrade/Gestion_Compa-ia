package com.JVNTecnologias.gestion_compania.controller;


import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.service.IGestionCompaniaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/companias", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Compañia", description = "Operaciones relacionadas con las compañias")
@AllArgsConstructor
public class GestionCompaniaController {

    IGestionCompaniaService iGestionCompaniaService;

    @PostMapping("/guardar")
    @Operation(summary = "Método que permite la creación de una nueva compañía.", description = "Devuelve objecto generico con la compañia creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea de manera correcta la compañía.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "[{\"id\":1,\"name\":\"Software Tech \",\"email\":\"data@example.com\"}]")))
    })
    public ResponseEntity<ResponseGenerico> guardar(@Valid @RequestBody CompaniaRequestDto companiaRequestDto) {
        ResponseGenerico servicio = this.iGestionCompaniaService.guardar(companiaRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }
}
