package com.besy.bcsb.servicios.implementacionesDataBase;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.dto.FechasDto;
import com.besy.bcsb.dto.mapper.IPeliculaSerieMapper;
import com.besy.bcsb.dto.request.PeliculaSerieRequestDto;
import com.besy.bcsb.dto.response.PeliculaSerieResponseDto;
import com.besy.bcsb.repositorios.memory.interfaces.IPeliculaSerieRepository;
import com.besy.bcsb.servicios.interfaces.IGeneroService;
import com.besy.bcsb.servicios.interfaces.IPeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty (prefix = "app", name = "type-data", havingValue = "database")

public class PeliculaSerieServiceImpl implements IPeliculaSerieService {

    @Autowired
    IGeneroService generoService;

    @Autowired
    IPeliculaSerieRepository peliculaSerieRepository;

    @Autowired
    IPeliculaSerieMapper peliculaSerieMapper;


    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerieResponseDto> buscarPorParametros(String titulo, String nombreGenero) {
        List<PeliculaSerie> peliculas = this.peliculaSerieRepository.buscarPorParamatros(titulo, nombreGenero);
        List<PeliculaSerieResponseDto> peliculasResponseDto = new ArrayList<>();
        for (PeliculaSerie pelicula : peliculas) {
            peliculasResponseDto.add(this.peliculaSerieMapper.mapToDto(pelicula));
        }
        return peliculasResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerieResponseDto> buscarPorCalificaciones(Byte desde, Byte hasta) {
        if (desde == null || hasta == null || desde < 1 || hasta > 5 || desde > hasta) {
            throw new IllegalArgumentException("Par??metros de b??squeda no v??lidos");
        }
        List<PeliculaSerie> peliculas = this.peliculaSerieRepository.buscarPorCalificaciones(desde, hasta);
        return peliculas.stream().map(this.peliculaSerieMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerieResponseDto> buscarPorFechas(FechasDto fechasDto) {
        try {
            LocalDate fechaInicio = LocalDate.parse(fechasDto.getDesde(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate fechaFinal = LocalDate.parse(fechasDto.getHasta(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (fechaInicio.isAfter(fechaFinal)) {
                throw new IllegalArgumentException("Rango de fecha inv??lido.");
            }
            if (fechaInicio.isAfter(LocalDate.now()) || fechaFinal.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha no puede ser del futuro.");
            }
            List<PeliculaSerie> peliculas = this.peliculaSerieRepository.buscarPorFechas(fechaInicio, fechaFinal);
            return peliculas.stream().map(peliculaSerieMapper::mapToDto).collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inv??lido.");
        }
    }
    /*
    se realizan las siguientes validaciones en Crear:
        Si la pel??cula/serie es nula
        Si el t??tulo de la pel??cula/serie es nulo o vac??o
        Si la pel??cula/serie ya existe
        Si el nombre del g??nero es nulo o vac??o
        Si existe el g??nero con ese nombre
    */
    @Override
    @Transactional(readOnly = false)
    public PeliculaSerieResponseDto crear(PeliculaSerieRequestDto dto) {
        PeliculaSerie peliculaSerie = peliculaSerieMapper.mapToEntity(dto);

        if (peliculaSerie == null) {
            throw new IllegalArgumentException("La pel??cula/serie no puede ser nula.");
        }
        if (peliculaSerie.getTitulo() == null || peliculaSerie.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El t??tulo de la pel??cula/serie no puede ser nulo o vac??o.");
        }
        if (this.peliculaSerieRepository.buscarPorSerieTitulo(peliculaSerie.getTitulo())) {
            throw new IllegalArgumentException("La pel??cula/serie ya existe.");
        }
        if (peliculaSerie.getGenero() == null || peliculaSerie.getGenero().getNombre() == null
                || peliculaSerie.getGenero().getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del g??nero no puede ser nulo o vac??o.");
        }
        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());
        if (optionalGenero.isEmpty()) {
            throw new IllegalArgumentException("No existe g??nero con ese nombre.");
        }
        peliculaSerie.setGenero(optionalGenero.get());

        peliculaSerie = this.peliculaSerieRepository.guardar(peliculaSerie);
        return peliculaSerieMapper.mapToDto(peliculaSerie);
    }

    @Override
    @Transactional(readOnly = false)
    public PeliculaSerieResponseDto actualizar(Long id, PeliculaSerieRequestDto dto) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("El ID no puede ser nulo o menor a 1.");
        }

        PeliculaSerie peliculaSerie = peliculaSerieMapper.mapToEntity(dto);

        if (peliculaSerie == null) {
            throw new IllegalArgumentException("La pel??cula/serie no puede ser nula.");
        }

        if (peliculaSerie.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El t??tulo no puede ser nulo o vac??o.");
        }

        if (this.peliculaSerieRepository.buscarPorSerieTitulo(peliculaSerie.getTitulo())) {
            throw new IllegalArgumentException("Ya existe una pel??cula/serie con ese t??tulo.");
        }

        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if (optionalGenero.isEmpty()) {
            throw new IllegalArgumentException("No existe g??nero con ese nombre.");
        }

        Genero genero = optionalGenero.get();
        peliculaSerie.setGenero(genero);
        peliculaSerie.setId(id);

        peliculaSerie = this.peliculaSerieRepository.editar(id, peliculaSerie);
        return peliculaSerieMapper.mapToDto(peliculaSerie);
    }
}
