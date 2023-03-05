package com.besy.bcsb.servicios.interfaces;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.dto.request.PersonajeRequestDto;
import com.besy.bcsb.dto.response.PersonajeResponseDto;

import java.util.List;

public interface IPersonajeService {

    List<PersonajeResponseDto> buscarPorNombreYEdad(String nombre, Integer edad);
    List<PersonajeResponseDto> buscarPorEdades(Integer desde, Integer hasta);
    PersonajeResponseDto crear(PersonajeRequestDto personajeNuevo);
    PersonajeResponseDto actualizar(Long id, PersonajeRequestDto personaje);
}
