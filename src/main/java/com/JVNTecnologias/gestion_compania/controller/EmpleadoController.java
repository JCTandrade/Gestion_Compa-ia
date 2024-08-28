package com.JVNTecnologias.gestion_compania.controller;

import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.EmpleadoRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.service.IEmpleadoService;
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
@RequestMapping(value = "v1/empleado", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Empleado", description = "Operaciones relacionadas con los empleados")
@AllArgsConstructor
public class EmpleadoController {

    IEmpleadoService iEmpleadoService;

    @PostMapping("/guardar")
    @Operation(summary = "Método que permite la creación de un empleado.", description = "Devuelve objecto generico con el empleado creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se crea de manera correcta el empleado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"message\":\"Se ha guardado el empleado\"," +
                                    "\"data\":{\"idEmpleado\":52,\"nombre\":\"Carlos Marcos\"," + "\"apellido\":\"Menesses\"," +
                                    "\"telefono\":\"3214567890\",\"email\":\"carlos@gmail.com\",\"createdAt\":\"2024-08-24\",\"updatedAt\":null}}"
                            )))
    })
    public ResponseEntity<ResponseGenerico> guardar(@Valid @RequestBody EmpleadoRequestDto empleadoRequestDto) {
        ResponseGenerico servicio = this.iEmpleadoService.guardar(empleadoRequestDto);
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar empleados existentes", description = "Método que se encarga de listar todos los empleados registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empleados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGenerico.class),
                            examples = @ExampleObject(name = "example1", value = "{\"status\":\"OK\",\"estadoOperacion\":\"SUCCESS\"," +
                                    "\"message\":\"Se ha consultado de manera correcta.\",\"data\":[{\"idCompania\":1,\"nombre\":\"Empresas olimpica S.A\"," +
                                    "\"nit\":\"123456-7363\",\"propietario\":\"Carlos mario Tovar\",\"direccion\":\"Calle 40 # 31-3A\"," +
                                    "\"telefono\":\"0018002882\",\"email\":\"Olimpica@gmail.com\",\"createdAt\":\"2024-08-26\",\"updatedAt\":null," +
                                    "\"estadoRegistro\":ACTIVO},{\"idCompania\":2,\"nombre\":\"Exito S.A\",\"nit\":\"123423456-7363\"," +
                                    "\"propietario\":\"Pedro mario Tovar\",\"direccion\":\"Calle 41 # 31-3A\",\"telefono\":\"0018002882\"," +
                                    "\"email\":\"exito@gmail.com\",\"createdAt\":\"2024-08-26\",\"updatedAt\":null,\"estadoRegistro\":ACTIVO}]}"
                            )))
    })
    public ResponseEntity<ResponseGenerico> listar() {
        ResponseGenerico servicio = this.iEmpleadoService.listar();
        return new ResponseEntity<>(servicio, servicio.getStatus());
    }
}
