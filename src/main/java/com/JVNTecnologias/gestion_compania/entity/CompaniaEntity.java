package com.JVNTecnologias.gestion_compania.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="compania")
public class CompaniaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_compania")
    private Long idCompania;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nit")
    private String nit;

    @Column(name = "propietario")
    private String propietario;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_creacion")
    private LocalDate createdAt;

    @Column(name = "fecha_actualizacion")
    private String updatedAt;
}
