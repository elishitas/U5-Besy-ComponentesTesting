package com.besy.bcsb.servicios.implementacionesMemoria;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.dto.mapper.IPersonajeMapper;
import com.besy.bcsb.dto.request.PersonajeRequestDto;
import com.besy.bcsb.dto.response.PersonajeResponseDto;
import com.besy.bcsb.repositorios.memory.interfaces.IPersonajeRepository;
import com.besy.bcsb.servicios.interfaces.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")

public class PersonajeServiceMemoriaImpl implements IPersonajeService {

    @Autowired
    private IPersonajeRepository repository;

    @Autowired
    private IPersonajeMapper personajeMapper;

    @Override
    public List<PersonajeResponseDto> buscarPorNombreYEdad(String nombre, Integer edad) {
            List<Personaje> personajes = repository.buscarPorNombreYEdad(nombre, edad);
            List<PersonajeResponseDto> personajeDto = personajes.stream()
                    .map(personajeMapper::mapToDto)
                    .collect(Collectors.toList());

            return personajeDto;
        }

    @Override
    public List<PersonajeResponseDto> buscarPorEdades(Integer desde, Integer hasta) {
            if (desde < 0 || hasta < 0) {
                throw new IllegalArgumentException("Los valores de desde y hasta deben ser mayores o iguales a cero.");
            }
            if (desde > hasta) {
                throw new IllegalArgumentException("El valor de desde no puede ser mayor que el valor de hasta.");
            }
            List<Personaje> personajes = this.repository.buscarPorEdades(desde, hasta);
            List<PersonajeResponseDto> personajesDto = personajes.stream()
                    .map(personajeMapper::mapToDto)
                    .collect(Collectors.toList());
            return personajesDto;
        }

    @Override
    public PersonajeResponseDto crear(PersonajeRequestDto personajeNuevo) {
        if (personajeNuevo == null) {
            throw new IllegalArgumentException("El personaje no puede ser nulo.");
        }
        if (personajeNuevo.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede ser una cadena vacía.");
        }

        Personaje personaje = personajeMapper.mapToEntity(personajeNuevo);
        Personaje creado = repository.crear(personaje);
        return personajeMapper.mapToDto(creado);
    }

    @Override
    public PersonajeResponseDto actualizar(Long id, PersonajeRequestDto personajeActual) {
        if (personajeActual == null) {
            throw new IllegalArgumentException("No se ha encontrado ningún personaje con el id especificado.");
        }
        Personaje personaje = repository.existePersonajePorId(Long id);
        personaje.setNombre(personajeActual.getNombre());
        personaje.setEdad(personajeActual.getEdad());

        Personaje actualizado = repository.actualizar(personaje);
        return personajeMapper.mapToDto(actualizado);
    }
}
