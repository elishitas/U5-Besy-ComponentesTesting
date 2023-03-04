package com.besy.bcsb.repositorios.database;

import com.besy.bcsb.dominio.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonajeRepository extends JpaRepository<Personaje, Integer> {
    @Query("select per from Personaje per where per.nombre=?1")
    List<Personaje> findByName(String nombre);
    @Query("select per from Personaje per where per.edad=?1")
    List<Personaje> findByAge(int edad);
    @Query("select per from Personaje per where per.edad>=?1 and per.edad<=?2")
    List<Personaje> findBetweenAges(int desde, int hasta);
}
