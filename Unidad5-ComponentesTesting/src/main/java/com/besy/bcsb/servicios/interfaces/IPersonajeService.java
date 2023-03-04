package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dominio.Personaje;

import java.util.List;

public interface IPersonajeService {

    List<Personaje> buscarPorNombreYEdad(String nombre, Integer edad);
    List<Personaje> buscarPorEdades(Integer desde, Integer hasta);
    Personaje crear(Personaje personaje);
    Personaje actualizar(Long id, Personaje personaje);
}
