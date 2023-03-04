package com.besy.bcsb.repositorios.memory.interfaces;

import com.besy.bcsb.dominio.PeliculaSerie;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPeliculaSerieRepository {
    List<PeliculaSerie> buscarPorParamatros(String titulo, String nombreGenero);
    List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta);
    List<PeliculaSerie> buscarPorFechas(LocalDate desde, LocalDate hasta);


    PeliculaSerie guardar(PeliculaSerie peliculaSerie);
    PeliculaSerie editar(Long id, PeliculaSerie peliculaSerie);


    boolean buscarPorId(Long id);
    boolean buscarPorSerieTitulo(String titulo);

}
