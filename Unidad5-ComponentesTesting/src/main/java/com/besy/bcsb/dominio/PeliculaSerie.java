package com.besy.bcsb.dominio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "peliculas_series")
@NoArgsConstructor
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 50, name = "TITULO", nullable = false, unique = true)
    private String titulo;

    @Column(name = "FECHA_DE_CREACION", nullable = false)
    private LocalDate fechaDeCreacion;

    @Column( name = "CALIFICACION", nullable = false)
    private byte calificacion;

    @ManyToOne
    @JoinColumn(name = "GENERO_ID", nullable = false)
    private Genero genero;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personajes_peliculas_series",
            joinColumns = @JoinColumn(name = "PELICULA_SERIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSONAJE_ID")
    )
    private List<Personaje> personajes;

     public PeliculaSerie(Long id, String titulo, LocalDate fechaDeCreacion, byte calificacion, Genero genero) {
         this.id = id;
         this.titulo = titulo;
         this.fechaDeCreacion = fechaDeCreacion;
         this.calificacion = calificacion;
         this.genero = genero;
         this.personajes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
         return titulo;
    }

    public void setTitulo(String titulo) {
         this.titulo = titulo;
    }

    public LocalDate getFechaDeCreacion() {
         return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
         this.fechaDeCreacion = fechaDeCreacion;
    }

    public byte getCalificacion() {
         return calificacion;
    }

    public void setCalificacion(byte calificacion) {
         this.calificacion = calificacion;
    }

    public Genero getGenero() {
         return genero;
    }

    public void setGenero(Genero genero) {
         this.genero = genero;
    }
}
