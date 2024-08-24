package com.JVNTecnologias.gestion_compania.service;

import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;

public interface IGestionCompaniaService {

    ResponseGenerico guardar(CompaniaRequestDto companiaRequestDto);
}
