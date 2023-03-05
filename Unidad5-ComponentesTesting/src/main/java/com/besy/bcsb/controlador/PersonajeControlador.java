package com.besy.bcsb.controlador;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.servicios.interfaces.IPersonajeService;
import com.besy.bcsb.utilidades.DatosUtilidad;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personajes")
public class PersonajeControlador {

    private final IPersonajeService personajeService;

    public PersonajeControlador(IPersonajeService personajeService) {
        this.personajeService = personajeService;
    }


    @GetMapping
    public ResponseEntity<?> buscarPorNombreYEdad(@RequestParam(required = false) String nombre,
                                                  @RequestParam(required = false) Integer edad) {
        try {
            return ResponseEntity.ok(this.personajeService.buscarPorNombreYEdad(nombre, edad));
        } catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    /*
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity filtrarPorNombre(@PathVariable("nombre") String nombrePersonaje) {
        try {
            return ResponseEntity.ok().body(DatosUtilidad.filtrarPersonajesPorNombre(nombrePersonaje));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

     */

    @GetMapping("/edades")
    public ResponseEntity<?> buscarPorEdades(@RequestParam(required = false) Integer desde,
                                             @RequestParam(required = false) Integer hasta) {
        try {
            return ResponseEntity.ok(this.personajeService.buscarPorEdades(desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    /*
    @GetMapping("/edades")
    public ResponseEntity filtrarPorRangoDeEdad(@RequestParam int desde, @RequestParam int hasta){
        try {
            return new ResponseEntity(DatosUtilidad.filtrarPorRangoDeEdad(desde, hasta), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
 */
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Personaje personaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.personajeService.crear(personaje));
        } catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Personaje personaje) {
        try {
            return ResponseEntity.ok(this.personajeService.actualizar(id, personaje));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}


