//metodos necesarios de negocios
package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dto.request.PeliculaSerieRequestDto;
import com.besy.bcsb.dto.response.PeliculaSerieResponseDto;
import com.besy.bcsb.dto.FechasDto;

import java.util.List;

public interface IPeliculaSerieService {

    List<PeliculaSerieResponseDto> buscarPorParametros(String titulo, String nombreGenero);

    List<PeliculaSerieResponseDto> buscarPorCalificaciones(Byte desde, Byte hasta);

    List<PeliculaSerieResponseDto> buscarPorFechas(FechasDto fechasDto);


    PeliculaSerieResponseDto crear(PeliculaSerieRequestDto dto);
    PeliculaSerieResponseDto actualizar(Long id, PeliculaSerieRequestDto dto);
}