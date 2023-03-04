//metodos necesarios de negocios
package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dominio.PeliculaSerie;

import java.time.LocalDate;
import java.util.List;

public interface IPeliculaSerieService {

    List<PeliculaSerie> buscarPorParametros(String titulo, String nombreGenero);

    List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta);
    List<PeliculaSerie> buscarPorFechas(String desde, String hasta);


    PeliculaSerie crear(PeliculaSerie peliculaSerie);
    PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie);
}
