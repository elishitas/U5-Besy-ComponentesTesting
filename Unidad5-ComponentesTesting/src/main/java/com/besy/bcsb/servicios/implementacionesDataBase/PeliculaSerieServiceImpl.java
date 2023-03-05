package com.besy.bcsb.servicios.implementacionesDataBase;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@ConditionalOnProperty (prefix = "app", name = "type-data", havingValue = "database")

public class PeliculaSerieServiceImpl implements IPeliculaSerieService {

    @Autowired
    IGeneroService generoService;

    @Autowired
    IPeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieServiceImpl(IGeneroService generoService,
                                    IPeliculaSerieRepository peliculaSerieRepository) {
        this.generoService = generoService;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerie> buscarPorParametros(String titulo, String nombreGenero) {
        return this.peliculaSerieRepository.buscarPorParamatros(titulo, nombreGenero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta) {

        if (desde == null || hasta == null || desde < 1 || hasta > 5 || desde > hasta) {
            throw new IllegalArgumentException("Parámetros de búsqueda no válidos");
        }
        return this.peliculaSerieRepository.buscarPorCalificaciones(desde, hasta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaSerie> buscarPorFechas(String desde, String hasta) {
        try {
            LocalDate fechaInicio = LocalDate.parse(desde, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate fechaFinal = LocalDate.parse(hasta, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (fechaInicio.isAfter(fechaFinal)) {
                throw new IllegalArgumentException("Rango de fecha inválido.");
            }
            if (fechaInicio.isAfter(LocalDate.now()) || fechaFinal.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha no puede ser del futuro.");
            }
            return this.peliculaSerieRepository.buscarPorFechas(fechaInicio, fechaFinal);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido.");
        }
    }

    /*
    se realizan las siguientes validaciones en Crear:
        Si la película/serie es nula
        Si el título de la película/serie es nulo o vacío
        Si la película/serie ya existe
        Si el nombre del género es nulo o vacío
        Si existe el género con ese nombre
    */
    @Override
    @Transactional(readOnly = false)
    public PeliculaSerie crear(PeliculaSerie peliculaSerie) {
        if (peliculaSerie == null) {
            throw new IllegalArgumentException("La película/serie no puede ser nula.");
        }
        if (peliculaSerie.getTitulo() == null || peliculaSerie.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título de la película/serie no puede ser nulo o vacío.");
        }
        if (this.peliculaSerieRepository.buscarPorSerieTitulo(peliculaSerie.getTitulo())) {
            throw new IllegalArgumentException("La película/serie ya existe.");
        }
        if (peliculaSerie.getGenero() == null || peliculaSerie.getGenero().getNombre() == null
                || peliculaSerie.getGenero().getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del género no puede ser nulo o vacío.");
        }
        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());
        if (optionalGenero.isEmpty()) {
            throw new IllegalArgumentException("No existe género con ese nombre.");
        }
        peliculaSerie.setGenero(optionalGenero.get());
        return this.peliculaSerieRepository.guardar(peliculaSerie);
    }

    @Override
    @Transactional(readOnly = false)
    public PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("El ID no puede ser nulo o menor a 1.");
        }

        if (peliculaSerie == null) {
            throw new IllegalArgumentException("La película/serie no puede ser nula.");
        }

        if (peliculaSerie.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }

        if (this.peliculaSerieRepository.buscarPorSerieTitulo(peliculaSerie.getTitulo())) {
            throw new IllegalArgumentException("Ya existe una película/serie con ese título.");
        }

        Optional<Genero> optionalGenero = this.generoService.buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if (optionalGenero.isEmpty()) {
            throw new IllegalArgumentException("No existe género con ese nombre.");
        }

        Genero genero = optionalGenero.get();
        peliculaSerie.setGenero(genero);
        peliculaSerie.setId(id);

        return this.peliculaSerieRepository.editar(id, peliculaSerie);
    }
}
