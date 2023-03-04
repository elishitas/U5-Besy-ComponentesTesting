package com.besy.bcsb.dominio;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(length = 30, name = "NOMBRE", nullable = false, unique = true)
    private String nombre;

    public Genero() {
    }

    public Genero(Long Id, String nombre) {
        this.Id = Id;
        this.nombre = nombre;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
