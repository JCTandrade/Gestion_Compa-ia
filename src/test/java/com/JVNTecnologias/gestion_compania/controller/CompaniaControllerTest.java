package com.JVNTecnologias.gestion_compania.controller;

import com.JVNTecnologias.gestion_compania.constant.Constant;
import com.JVNTecnologias.gestion_compania.dto.CompaniaRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.service.ICompaniaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

class CompaniaControllerTest {

    private String URL = "/v1/companias/";
    private MockMvc mockMvc;

    @Mock
    private ICompaniaService iCompaniaService;

    @InjectMocks
    private CompaniaController companiaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companiaController).build();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGuardarCompaniaOK() throws Exception {
        CompaniaRequestDto requestDto = new CompaniaRequestDto();
        requestDto.setNombre("Empresas olimpica S.A");
        requestDto.setNit("123456-7363");
        requestDto.setPropietario("Carlos mario Tovar");
        requestDto.setDireccion("Calle 40 # 31-3A");
        requestDto.setTelefono("0018002882");
        requestDto.setEmail("Olimpica@gmail.com");


        ResponseGenerico response = new ResponseGenerico();
        response.setStatus(HttpStatus.OK);
        response.setMessage(Constant.Message.COMPANIA_GUARDADA);
        response.setData(requestDto);


        when(iCompaniaService.guardar(any(CompaniaRequestDto.class))).thenReturn(response);


        mockMvc.perform(post(URL+ "guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("Se ha guardado el compania"))
                .andExpect(jsonPath("$.data.nombre").value("Empresas olimpica S.A"))
                .andExpect(jsonPath("$.data.nit").value("123456-7363"))
                .andExpect(jsonPath("$.data.propietario").value("Carlos mario Tovar"))
                .andExpect(jsonPath("$.data.direccion").value("Calle 40 # 31-3A"))
                .andExpect(jsonPath("$.data.telefono").value("0018002882"))
                .andExpect(jsonPath("$.data.email").value("Olimpica@gmail.com"));


        verify(iCompaniaService, times(1)).guardar(any(CompaniaRequestDto.class));
    }
}