package com.JVNTecnologias.gestion_compania.service;

import com.JVNTecnologias.gestion_compania.dto.EmpleadoRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;

public interface IEmpleadoService {
    ResponseGenerico guardar(EmpleadoRequestDto empleadoRequestDto);
    ResponseGenerico listar();
    ResponseGenerico buscarPorId(Long id);
    ResponseGenerico actualizar(EmpleadoRequestDto empleadoRequestDto);
    ResponseGenerico eliminar(Long id);
}
