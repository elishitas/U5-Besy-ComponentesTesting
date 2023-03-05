package com.besy.bcsb.controlador;

import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.dto.FechasDto;
import com.besy.bcsb.dto.request.PeliculaSerieRequestDto;
import com.besy.bcsb.dto.response.PeliculaSerieResponseDto;
import com.besy.bcsb.servicios.interfaces.IPeliculaSerieService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas-series")
public class PeliculaSerieControlador {

    private final IPeliculaSerieService peliculaSerieService;

    public PeliculaSerieControlador(IPeliculaSerieService peliculaSerieService) {
        this.peliculaSerieService = peliculaSerieService;
    }

    //http://localhost:9080/peliculas-series/
    @GetMapping()
    public ResponseEntity<List<PeliculaSerieResponseDto>> buscarPorParametros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String nombreGenero) {

        List<PeliculaSerie> peliculasSeries = peliculaSerieService.buscarPorParametros(titulo, nombreGenero);

        HttpStatus status = (peliculasSeries.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(status).body(peliculasSeries);
    }

    /*
    //http://localhost:9080/peliculas-series/titulo/Zoolander
    @GetMapping("titulo/{titulo}")
    public ResponseEntity<?> buscarporTitulo(@PathVariable("titulo") String titulo) {
        return ResponseEntity.ok().body(DatosUtilidad.filtrarPeliculasSeriesPorTitulo(titulo));
    }
    */

    //3
    //http://localhost:9080/peliculas-series/calificaciones?desde=3&hasta=5
    @GetMapping("/calificaciones")
    public ResponseEntity<?> buscarPorCalificaciones(@RequestParam Byte desde,
                                                     @RequestParam Byte hasta) {

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorCalificaciones(desde, hasta));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    //2
    //http://localhost:9080/peliculas-series/fechas?desde=31-03-2016&hasta=31-03-2022
    @GetMapping("/fechas")
    public ResponseEntity<?> buscarPorFechas(@ModelAttribute FechasDto fechasDto) {
        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorFechas(fechasDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

     //4
    // post http://localhost:9091/peliculas-series
    /*
{
    "id": 11,
    "titulo": "Spiderman Animado",
    "fechaDeCreacion": "2022-02-02",
    "calificacion": 5,
    "genero": {
        "nombre": "Infantil",
        "id": 5
    }
}
    */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PeliculaSerieRequestDto dto) {
        try {
            PeliculaSerie peliculaSerie = peliculaSerieMapper.mapToEntity(dto);
            PeliculaSerieResponseDto responseDto = peliculaSerieMapper.mapToDto(peliculaSerieService.crear(peliculaSerie));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //5
    /*
    {
        "id": 1,
        "titulo": "La Nochecita de los Museos",
        "fechaDeCreacion": "2006-03-31",
        "calificacion": 5,
        "genero": {
            "nombre": "Aventura",
            "id": 1
        }
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody PeliculaSerie peliculaSerie){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.actualizar(id, peliculaSerie));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}