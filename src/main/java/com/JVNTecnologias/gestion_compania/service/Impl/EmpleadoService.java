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
import com.JVNTecnologias.gestion_compania.mapper.SucursalMapper;
import com.JVNTecnologias.gestion_compania.repository.EmpleadoRepository;
import com.JVNTecnologias.gestion_compania.repository.SucursalRepository;
import com.JVNTecnologias.gestion_compania.service.ICompaniaService;
import com.JVNTecnologias.gestion_compania.service.IEmpleadoService;
import com.JVNTecnologias.gestion_compania.service.ISucursalService;
import com.JVNTecnologias.gestion_compania.utils.GeneradorRespuesta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class EmpleadoService implements IEmpleadoService {

    private final GeneradorRespuesta generadorRespuesta;
    EmpleadoRepository empleadoRepository;
    ISucursalService iSucursalService;
    private final EmpleadoMapper empleadoMapper = EmpleadoMapper.INSTANCE;

    @Override
    public ResponseGenerico guardar(EmpleadoRequestDto empleadoRequestDto) {
        SucursalEntity sucursalEntity = this.iSucursalService.buscarPorIdEntity(empleadoRequestDto.getIdSucursal());
        if (sucursalEntity == null) {
            return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.WARNING,"La sucursal para crear el empleado no existe", null);
        }
        if (sucursalEntity.getEstadoRegistro().equals(EstadoRegistroEnum.INACTIVO)){
            return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.WARNING,"La sucursal se encuentra en estado inactiva", null);
        }
        EmpleadosEntity empleadosEntity = this.empleadoMapper.toEmpleadosEntity(empleadoRequestDto);
        empleadosEntity.setCreatedAp(LocalDate.now());
        empleadosEntity.setSucursal(sucursalEntity);
        empleadosEntity.setEstadoRegistro(EstadoRegistroEnum.ACTIVO);

        empleadosEntity = this.empleadoRepository.save(empleadosEntity);

        //TODO se debe crear una validacion para validar si se crea el empleado con su Id

        EmpleadoDto empleadoDto = this.empleadoMapper.toEmpleadoDto(empleadosEntity);

        return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.OPERACION_EXITO, empleadoDto);
    }

    @Override
    public ResponseGenerico listar() {
        return this.generadorRespuesta.generarRespuesta(HttpStatus.OK, EstadosEnum.SUCCESS, Constant.Message.OPERACION_EXITO, this.empleadoRepository.findAll());
    }

    @Override
    public ResponseGenerico buscarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseGenerico actualizar(EmpleadoRequestDto empleadoRequestDto) {
        return null;
    }

    @Override
    public ResponseGenerico eliminar(Long id) {
        return null;
    }
}
