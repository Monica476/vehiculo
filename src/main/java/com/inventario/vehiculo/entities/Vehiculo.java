package com.inventario.vehiculo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "VEHICULO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String marca;
    private String modelo;

    private String matricula;
    private String color;

    private Integer anio;


}
