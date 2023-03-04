package com.besy.bcsb.repositorios.memory.interfaces;

import com.besy.bcsb.dominio.Personaje;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository {

    List<Personaje> buscarPorNombreYEdad(String nombre, Integer edad);

    List<Personaje> buscarPorEdades(Integer desde, Integer hasta);

    Personaje crear(Personaje personaje);
    Personaje actualizar(Long id, Personaje personaje);
    boolean existePersonajePorId(Long id);

    List<Personaje> buscarTodos();
}
