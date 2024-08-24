package com.JVNTecnologias.gestion_compania.mapper;

import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.entity.CompaniaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompaniasMapper {
    CompaniasMapper INSTANCE = Mappers.getMapper(CompaniasMapper.class);

    CompaniaEntity toEntity(CompaniaRequestDto entity);
}
