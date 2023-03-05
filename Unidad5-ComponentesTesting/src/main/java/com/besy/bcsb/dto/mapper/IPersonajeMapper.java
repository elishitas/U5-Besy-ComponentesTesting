package com.besy.bcsb.dto.mapper;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.dto.request.PersonajeRequestDto;
import com.besy.bcsb.dto.response.PersonajeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPersonajeMapper {

    Personaje mapToEntity(PersonajeRequestDto dto);
    PersonajeResponseDto mapToDto(Personaje personaje);
}
