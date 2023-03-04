
package com.besy.bcsb.controlador;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
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

    //http://localhost:9080/generos
    @GetMapping
    ResponseEntity<List<Genero>> obtenerTodos() {
        return ResponseEntity.ok(this.generoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<?> crearGenero(@RequestBody Genero genero) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.generoService.crearGeneros(genero));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /*
     {
            "nombre": "Comediana",
        }
    */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGenero(@PathVariable Long id,
                                              @RequestBody Genero genero) {

        Genero resultado = this.generoService.actualizarGenero(id, genero);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        } else {
            throw new RuntimeException("No se pudo actualizar el genero.");
        }
    }


}