package com.JVNTecnologias.gestion_compania.service.Impl;

import com.JVNTecnologias.gestion_compania.constant.Constant;
import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.entity.CompaniaEntity;
import com.JVNTecnologias.gestion_compania.mapper.CompaniasMapper;
import com.JVNTecnologias.gestion_compania.repository.GestionCompaniaRepository;
import com.JVNTecnologias.gestion_compania.utils.GeneradorRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GestionCompaniaImplServiceTest {

    @InjectMocks GestionCompaniaImplService gestionCompaniaImplService;

    @Mock
    GestionCompaniaRepository gestionCompaniaRepository;

    @Mock
    GeneradorRespuesta generadorRespuesta;

    @Mock
    CompaniasMapper companiasMapper;

    CompaniaRequestDto companiaRequestDto;
    CompaniaEntity companiaEntity;

    ResponseGenerico responseGenerico;

    @BeforeEach
    void setUp() {
        companiaRequestDto = new CompaniaRequestDto();
        companiaRequestDto.setNombre("Compania");
        companiaRequestDto.setDireccion("Calle 50 test");
        companiaRequestDto.setNit("2423423");
        companiaRequestDto.setTelefono("1234567890");
        companiaRequestDto.setEmail("email@email.com");

        companiaEntity = new CompaniaEntity();
        companiaEntity.setIdCompania(12L);

        responseGenerico = new ResponseGenerico();
        responseGenerico.setMessage(Constant.Message.COMPANIA_GUARDADA);
    }

    @Test
    void testGuardarCompaniaOK() {
        when(gestionCompaniaRepository.save(any())).thenReturn(companiaEntity);
        when(generadorRespuesta.generarRespuesta(any(),any(),any(),any())).thenReturn(responseGenerico);

        var servicio = this.gestionCompaniaImplService.guardar(companiaRequestDto);

        assertNotNull(servicio);
        assertEquals(responseGenerico, servicio);









    }
}