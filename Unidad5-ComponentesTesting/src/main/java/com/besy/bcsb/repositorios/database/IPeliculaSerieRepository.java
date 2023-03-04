package com.besy.bcsb.repositorios.database;

import com.besy.bcsb.dominio.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
    @Query("select ps from PeliculaSerie ps where ps.titulo=?1")
    Optional<PeliculaSerie> findByTitle(String titulo);

    @Query("select ps from PeliculaSerie ps where ps.fechaDeCreacion>=?1 and ps.fechaDeCreacion<=?2")
    Iterable<PeliculaSerie> findBetweenDates(LocalDate desde, LocalDate hasta);

    @Query("select ps from PeliculaSerie ps where ps.calificacion>=?1 and ps.calificacion<=?2")
    Iterable<PeliculaSerie> findBetweenCalif(byte desde, byte hasta);
}
