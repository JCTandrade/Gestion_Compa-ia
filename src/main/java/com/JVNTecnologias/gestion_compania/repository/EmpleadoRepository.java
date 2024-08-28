package com.JVNTecnologias.gestion_compania.repository;

import com.JVNTecnologias.gestion_compania.entity.EmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadosEntity, Long> {
}
