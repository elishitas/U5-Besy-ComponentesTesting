package com.besy.bcsb.dto.mapper;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dto.request.GeneroRequestDto;
import com.besy.bcsb.dto.response.GeneroResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGeneroMapper {

        @Mapping(target = "id", ignore = true)
        Genero mapToEntity(GeneroRequestDto dto);

        GeneroResponseDto mapToDto(Genero genero);
}

