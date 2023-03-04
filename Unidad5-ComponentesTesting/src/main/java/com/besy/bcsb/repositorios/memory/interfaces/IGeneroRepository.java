package com.besy.bcsb.repositorios.memory.interfaces;

import com.besy.bcsb.dominio.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroRepository {

    List<Genero> obtenerTodos();

    Genero crearGenero(Genero genero);

    Genero actualizarGenero(Long id, Genero genero);

    Optional<Genero> buscarPorId(Long id);

    boolean existePorNombre(String nombre);

    Optional<Genero> buscarPorNombre(String nombre);


}
