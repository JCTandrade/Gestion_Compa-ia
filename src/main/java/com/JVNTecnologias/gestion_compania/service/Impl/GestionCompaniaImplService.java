package com.JVNTecnologias.gestion_compania.service.Impl;

import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.entity.CompaniaEntity;
import com.JVNTecnologias.gestion_compania.mapper.CompaniasMapper;
import com.JVNTecnologias.gestion_compania.repository.GestionCompaniaRepository;
import com.JVNTecnologias.gestion_compania.service.IGestionCompaniaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class GestionCompaniaImplService implements IGestionCompaniaService {
    GestionCompaniaRepository gestionCompaniaRepository;
    private final CompaniasMapper companiaMapper = CompaniasMapper.INSTANCE;

    @Override
    public ResponseGenerico guardar(CompaniaRequestDto companiaRequestDto) {
        ResponseGenerico response = new ResponseGenerico();
        CompaniaEntity companiaMapperEntity = companiaMapper.toEntity(companiaRequestDto);
        companiaMapperEntity.setCreatedAt(LocalDate.now());

        CompaniaEntity saveCompania = this.gestionCompaniaRepository.save(companiaMapperEntity);

        if (saveCompania != null && saveCompania.getIdCompania() != null) {
            response.setData("Error creando la compania");
            response.setMessage("ERROR");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Se ha guardado el compania");
        response.setData(saveCompania);
        return response;
    }
}
