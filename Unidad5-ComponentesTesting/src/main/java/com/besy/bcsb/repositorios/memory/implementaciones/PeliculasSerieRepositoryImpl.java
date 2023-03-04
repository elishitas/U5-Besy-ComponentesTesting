package com.besy.bcsb.repositorios.memory.implementaciones;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.repositorios.memory.interfaces.IGeneroRepository;
import com.besy.bcsb.repositorios.memory.interfaces.IPeliculaSerieRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class PeliculasSerieRepositoryImpl implements IPeliculaSerieRepository {

    private final IGeneroRepository generoRepository;

    private List<Genero> generos;

    private  List<PeliculaSerie> peliculasSeries;

    private Long contador;

    public PeliculasSerieRepositoryImpl(IGeneroRepository generoRepository) {

        this.generoRepository = generoRepository;
        this.generos = generoRepository.obtenerTodos();
        this.contador = 1L;

        this.peliculasSeries = new ArrayList<>(
                Arrays.asList(
                        new PeliculaSerie(1L,"La Noche de los Museos", LocalDate.of(2006, 03, 31), (byte) 5, generos.get(0)),
                        new PeliculaSerie(2L,"La vida secreta de Walter Mitty", LocalDate.of(2013, 03, 31), (byte) 5, generos.get(0)),
                        new PeliculaSerie(3L,"Zoolander", LocalDate.of(2001, 03, 31), (byte) 1, generos.get(1)),
                        new PeliculaSerie(4L,"Loco por Mary", LocalDate.of(1998, 03, 31), (byte) 1, generos.get(1)),
                        new PeliculaSerie(5L,"Halftime", LocalDate.of(2022, 03, 31), (byte) 2, generos.get(2)),
                        new PeliculaSerie(6L,"Britney vs Spears", LocalDate.of(2021, 03, 31), (byte) 2, generos.get(2)),
                        new PeliculaSerie(7L,"La La Land", LocalDate.of(2016, 03, 31), (byte) 3, generos.get(3)),
                        new PeliculaSerie(8L,"Whiplash", LocalDate.of(2014, 03, 31), (byte) 3, generos.get(3)),
                        new PeliculaSerie(9L,"Red", LocalDate.of(2022, 03, 31), (byte) 4, generos.get(4)),
                        new PeliculaSerie(10L,"Luca", LocalDate.of(2021, 03, 31), (byte) 4, generos.get(4))
                ));

        // Encuentra el id más grande en la lista de películas
        for (PeliculaSerie peliculaSerie : this.peliculasSeries) {
            if (peliculaSerie.getId() > this.contador) {
                this.contador = peliculaSerie.getId();
            }
        }

        // Establece el contador en el siguiente número después del último id
        this.contador++;
    }

    @Override
    public List<PeliculaSerie> buscarPorParamatros(String titulo, String nombreGenero) {
        Predicate<PeliculaSerie> porTitulo = ps -> titulo == null || ps.getTitulo().equalsIgnoreCase(titulo);
        Predicate<PeliculaSerie> porGenero = ps -> nombreGenero == null || ps.getGenero().getNombre().equalsIgnoreCase(nombreGenero);

        return peliculasSeries.stream()
                .filter(porTitulo.and(porGenero))
                .collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta) {
        return peliculasSeries.stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion()<= hasta)
                .collect(Collectors.toList());
    }

    public List<PeliculaSerie> buscarPorFechas(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null) {
            throw new IllegalArgumentException("Los parámetros 'desde' y 'hasta' no pueden ser nulos.");
        }
        return peliculasSeries.stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(desde.minusDays(1)) && ps.getFechaDeCreacion().isBefore(hasta.plusDays(1)))
                .collect(Collectors.toList());
    }

    @Override
    public PeliculaSerie guardar(PeliculaSerie peliculaSerie) {
        peliculaSerie.setId(contador++);
        this.peliculasSeries.add(peliculaSerie);
        return peliculaSerie;
    }


    @Override
    public PeliculaSerie editar(Long id, PeliculaSerie peliculaSerie) {
        if(!buscarPorId(id)){
            throw new IllegalArgumentException("No existe pelicula/serie con ese ID.");
        }

        this.peliculasSeries.forEach((PeliculaSerie ps) -> {
            if(ps.getId().equals(id)){
                ps.setTitulo(peliculaSerie.getTitulo());
                ps.setCalificacion((byte) peliculaSerie.getCalificacion());
                ps.setFechaDeCreacion(peliculaSerie.getFechaDeCreacion());
                ps.setGenero(peliculaSerie.getGenero());
            }
        });

        return peliculaSerie;
    }

    @Override
    /*
     * Busca si existe una película o serie con el ID especificado.
     *
     * @param id el ID de la película o serie a buscar
     * @return true si existe una película o serie con ese ID, de lo contrario false
    */
    public boolean buscarPorId(Long id) {
        // Usa un stream para buscar una película o serie con el ID especificado.
        // anyMatch devuelve true si el predicado es verdadero para cualquier elemento.
        return this.peliculasSeries.stream()
                .anyMatch(ps -> ps.getId().equals(id));
    }

    @Override
    public boolean buscarPorSerieTitulo(String titulo) {
        return this.peliculasSeries.stream()
                .anyMatch(ps -> ps.getTitulo().equalsIgnoreCase(titulo));
    }
}
