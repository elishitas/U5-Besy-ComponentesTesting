package com.besy.bcsb.servicios.implementacionesMemoria;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.repositorios.memory.interfaces.IPersonajeRepository;
import com.besy.bcsb.servicios.interfaces.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")

public class PersonajeServiceMemoriaImpl implements IPersonajeService {

    @Autowired
    IPersonajeRepository personajeRepository;

    public PersonajeServiceMemoriaImpl(IPersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }
    @Override
    public List<Personaje> buscarPorNombreYEdad(String nombre, Integer edad) {
        if (nombre == null && edad == null) {
            return this.personajeRepository.buscarTodos();
        }
        return this.personajeRepository.buscarPorNombreYEdad(nombre, edad);

    }

    @Override
    public List<Personaje> buscarPorEdades(Integer desde, Integer hasta) {
        if (desde < 0 || hasta < 0) {
            throw new IllegalArgumentException("Los valores de desde y hasta deben ser mayores o iguales a cero.");
        }
        if (desde > hasta) {
            throw new IllegalArgumentException("El valor de desde no puede ser mayor que el valor de hasta.");
        }
        return this.personajeRepository.buscarPorEdades(desde, hasta);
    }

    @Override
    public Personaje crear(Personaje personaje) {
        if (personaje == null) {
            throw new IllegalArgumentException("El personaje no puede ser nulo.");
        }
        if (personaje.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede ser una cadena vacía.");
        }
        return this.personajeRepository.crear(personaje);
    }

    @Override
    public Personaje actualizar(Long id, Personaje personaje) {
        personaje.setId(id);
        return this.personajeRepository.actualizar(id, personaje);
    }
}
