//metodos necesarios de negocios
package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dto.request.GeneroRequestDto;
import com.besy.bcsb.dto.response.GeneroResponseDto;
import org.mapstruct.Mapper;


import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    List<GeneroResponseDto> obtenerTodos();

    GeneroResponseDto crearGeneros(GeneroRequestDto dto);

    GeneroResponseDto actualizarGenero(Long id, GeneroRequestDto dto);

    Optional<Genero> buscarPorNombre(String nombre);

}
