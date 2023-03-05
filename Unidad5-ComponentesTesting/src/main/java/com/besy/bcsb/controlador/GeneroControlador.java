
package com.besy.bcsb.controlador;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.dto.request.GeneroRequestDto;
import com.besy.bcsb.dto.response.GeneroResponseDto;
import com.besy.bcsb.servicios.interfaces.IGeneroService;
import com.besy.bcsb.utilidades.DatosUtilidad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")

public class GeneroControlador {

    private final IGeneroService generoService;

    public GeneroControlador(IGeneroService generoService) {
        this.generoService = generoService;
    }

    // http://localhost:9080/generos
    @GetMapping
    ResponseEntity<List<GeneroResponseDto>> obtenerTodos() {
        List<GeneroResponseDto> generos = this.generoService.obtenerTodos();
        return ResponseEntity.ok(generos);
    }

    // {
    //   "nombre": "Comedia"
    // }
    @PostMapping
    public ResponseEntity<?> crearGenero(@RequestBody GeneroRequestDto generoDto) {
        GeneroResponseDto nuevoGenero = this.generoService.crearGeneros(generoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGenero);
    }

    // {
    //   "nombre": "Comedia"
    // }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGenero(@PathVariable Long id,
                                              @RequestBody GeneroRequestDto generoDto) {
        GeneroResponseDto generoActualizado = this.generoService.actualizarGenero(id, generoDto);
        return ResponseEntity.ok(generoActualizado);
    }
}