package com.JVNTecnologias.gestion_compania.repository;

import com.JVNTecnologias.gestion_compania.entity.CompaniaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionCompaniaRepository extends JpaRepository<CompaniaEntity,Long> {
}
