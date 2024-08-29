package com.JVNTecnologias.gestion_compania.service.Impl;

import com.JVNTecnologias.gestion_compania.Enum.EstadoRegistroEnum;
import com.JVNTecnologias.gestion_compania.Enum.EstadosEnum;
import com.JVNTecnologias.gestion_compania.constant.Constant;
import com.JVNTecnologias.gestion_compania.dto.EmpleadoDto;
import com.JVNTecnologias.gestion_compania.dto.EmpleadoRequestDto;
import com.JVNTecnologias.gestion_compania.dto.ResponseGenerico;
import com.JVNTecnologias.gestion_compania.entity.EmpleadosEntity;
import com.JVNTecnologias.gestion_compania.entity.SucursalEntity;
import com.JVNTecnologias.gestion_compania.mapper.EmpleadoMapper;
import com.JVNTecnologias.gestion_compania.repository.EmpleadoRepository;
import com.JVNTecnologias.gestion_compania.utils.GeneradorRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmpleadoServiceTest {

    @InjectMocks
   EmpleadoService empleadoService;

    @Mock
    EmpleadoRepository empleadoRepository;

    @Mock
    EmpleadoMapper empleadoMapper;

    @Mock
    GeneradorRespuesta generadorRespuesta;

    @Mock
    SucursalImplService iSucursalService;

    EmpleadoRequestDto empleadoRequestDto;
    SucursalEntity sucursalEntity;

    EmpleadosEntity empleadosEntity;

    List<EmpleadosEntity> empleadosEntityList;
    List<EmpleadoDto> empleadoDtoList;
    EmpleadoDto empleadoDto;


    @BeforeEach
    void setUp() {
        empleadoRequestDto = new EmpleadoRequestDto();
        empleadoRequestDto.setIdSucursal(1L);
        empleadoRequestDto.setNombre("Nombre test");
        empleadoRequestDto.setApellido("Apellido test");

        sucursalEntity = new SucursalEntity();
        sucursalEntity.setEstadoRegistro(EstadoRegistroEnum.INACTIVO);

        empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setEstadoRegistro(EstadoRegistroEnum.ACTIVO);
        empleadosEntity.setSucursal(sucursalEntity);
        empleadosEntity.setNombre("Nombre test");
        empleadosEntity.setApellido("Apellido test");

        empleadosEntityList = new ArrayList<>();
        empleadosEntityList.add(empleadosEntity);

        empleadoDtoList = new ArrayList<>();
         empleadoDto = new EmpleadoDto();
        empleadoDto.setIdEmpleado(1L);
        empleadoDto.setNombre("Juan");
        empleadoDto.setApellido("Pérez");
        empleadoDtoList.add(empleadoDto);
    }


    @Test
    void guardarSucursalNoExiste() {
        when(iSucursalService.buscarPorIdEntity(empleadoRequestDto.getIdSucursal())).thenReturn(null);
        when(generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal para crear el empleado no existe", null))
                .thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal para crear el empleado no existe", null));

        ResponseGenerico respuesta = empleadoService.guardar(empleadoRequestDto);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.WARNING, respuesta.getEstadoOperacion());
        assertEquals("La sucursal para crear el empleado no existe", respuesta.getMessage());
        assertNull(respuesta.getData());
        verify(iSucursalService).buscarPorIdEntity(empleadoRequestDto.getIdSucursal());
    }

    @Test
    void guardarSucursalInactiva() {
        Long idSucursal = 1L;
        EmpleadoRequestDto empleadoRequestDto = new EmpleadoRequestDto();
        empleadoRequestDto.setIdSucursal(idSucursal);

        when(iSucursalService.buscarPorIdEntity(idSucursal)).thenReturn(sucursalEntity);
        when(generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal se encuentra en estado inactiva", null))
                .thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal se encuentra en estado inactiva", null));

        ResponseGenerico respuesta = empleadoService.guardar(empleadoRequestDto);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.WARNING, respuesta.getEstadoOperacion());
        assertEquals("La sucursal se encuentra en estado inactiva", respuesta.getMessage());
        assertNull(respuesta.getData());
        verify(iSucursalService).buscarPorIdEntity(idSucursal);
    }

    @Test
    void testGuardarSucursalNull() {
        when(iSucursalService.buscarPorIdEntity(empleadoRequestDto.getIdSucursal())).thenReturn(null);
        when(generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal para crear el empleado no existe", null))
                .thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.WARNING, "La sucursal para crear el empleado no existe", null));

        ResponseGenerico respuesta = empleadoService.guardar(empleadoRequestDto);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.WARNING, respuesta.getEstadoOperacion());
        assertNull(respuesta.getData());
    }

    @Test
    void testGuardarSucursalOK() {
        empleadosEntity.setIdEmpleado(23L);
        sucursalEntity.setEstadoRegistro(EstadoRegistroEnum.ACTIVO);
        when(iSucursalService.buscarPorIdEntity(empleadoRequestDto.getIdSucursal())).thenReturn(sucursalEntity);
        when(empleadoMapper.toEmpleadosEntity(empleadoRequestDto)).thenReturn(empleadosEntity);
        when(empleadoRepository.save(any(EmpleadosEntity.class))).thenReturn(empleadosEntity);
        when(empleadoMapper.toEmpleadoDto(empleadosEntity)).thenReturn(new EmpleadoDto());
        when(generadorRespuesta.generarRespuesta(
                eq(HttpStatus.OK),
                eq(EstadosEnum.SUCCESS),
                eq(Constant.Message.OPERACION_EXITO),
                any(EmpleadoDto.class)
        )).thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.OPERACION_EXITO, new EmpleadoDto()));


        ResponseGenerico respuesta = empleadoService.guardar(empleadoRequestDto);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.SUCCESS, respuesta.getEstadoOperacion());
        assertNotNull(respuesta.getData());
    }

    @Test
    void testListarEmpleadosNoExiste() {
        when(empleadoRepository.findAll()).thenReturn(Collections.emptyList());
        when(generadorRespuesta.noExiste())
                .thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.NO_DATA,null));

        ResponseGenerico respuesta = empleadoService.listar();

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.SUCCESS, respuesta.getEstadoOperacion());
        assertEquals(Constant.Message.NO_DATA,respuesta.getMessage());

    }

    @Test
    void testListarEmpleadosOk() {
        List<EmpleadosEntity> empleadosEntityList = new ArrayList<>();
        EmpleadosEntity empleado = new EmpleadosEntity();
        empleado.setIdEmpleado(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleadosEntityList.add(empleado);



        when(empleadoRepository.findAll()).thenReturn(empleadosEntityList);
        when(empleadoMapper.toEmpleadoDtos(empleadosEntityList)).thenReturn(empleadoDtoList);
        when(generadorRespuesta.generarRespuesta(
                eq(HttpStatus.OK),
                eq(EstadosEnum.SUCCESS),
                eq(Constant.Message.OPERACION_EXITO),
                eq(empleadoDtoList)
        )).thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.OPERACION_EXITO, empleadoDtoList));

        ResponseGenerico respuesta = empleadoService.listar();

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.SUCCESS, respuesta.getEstadoOperacion());
        assertEquals(Constant.Message.OPERACION_EXITO, respuesta.getMessage());
        assertNotNull(respuesta.getData());
        assertFalse(((List<?>) respuesta.getData()).isEmpty());
        assertEquals(1, ((List<?>) respuesta.getData()).size());
    }

    @Test
    void testBuscarPorIdEmpleadoExiste() {
        Long idEmpleado = 1L;
        EmpleadosEntity empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setIdEmpleado(idEmpleado);
        empleadosEntity.setNombre("Juan");
        empleadosEntity.setApellido("Pérez");

        when(empleadoRepository.findById(idEmpleado)).thenReturn(Optional.of(empleadosEntity));
        when(empleadoMapper.toEmpleadoDto(empleadosEntity)).thenReturn(empleadoDto);
        when(generadorRespuesta.generarRespuesta(
                eq(HttpStatus.OK),
                eq(EstadosEnum.SUCCESS),
                eq(Constant.Message.OPERACION_EXITO),
                eq(empleadoDto)
        )).thenReturn(new ResponseGenerico(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.OPERACION_EXITO, empleadoDto));

        ResponseGenerico respuesta = empleadoService.buscarPorId(idEmpleado);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(EstadosEnum.SUCCESS, respuesta.getEstadoOperacion());
        assertEquals(Constant.Message.OPERACION_EXITO, respuesta.getMessage());
        assertNotNull(respuesta.getData());
        assertEquals(empleadoDto, respuesta.getData());
    }


    @Test
    void testBuscarPorIdEmpleadoNoExiste() {
        Long idEmpleado = 1L;

        when(empleadoRepository.findById(idEmpleado)).thenReturn(Optional.empty());

        when(generadorRespuesta.noExiste()).thenReturn(new ResponseGenerico(HttpStatus.NOT_FOUND, EstadosEnum.ERROR, Constant.Message.NO_DATA, null));

        ResponseGenerico respuesta = empleadoService.buscarPorId(idEmpleado);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatus());
        assertEquals(EstadosEnum.ERROR, respuesta.getEstadoOperacion());
        assertEquals(Constant.Message.NO_DATA, respuesta.getMessage());
        assertNull(respuesta.getData());
    }




}