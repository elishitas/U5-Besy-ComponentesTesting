package com.besy.bcsb.repositorios.database;

import com.besy.bcsb.dominio.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGeneroRepository extends JpaRepository<Genero, Integer> {
    @Query("select g from Genero g where g.nombre=?1")
    Optional<Genero> buscarPorNombre(String nombre);
}
