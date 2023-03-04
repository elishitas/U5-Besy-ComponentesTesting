//metodos necesarios de negocios
package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dominio.Genero;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    List<Genero> obtenerTodos();

    Genero crearGeneros(Genero genero);

    Genero actualizarGenero(Long id, Genero genero);

    Optional<Genero> buscarPorNombre(String nombre);

}
