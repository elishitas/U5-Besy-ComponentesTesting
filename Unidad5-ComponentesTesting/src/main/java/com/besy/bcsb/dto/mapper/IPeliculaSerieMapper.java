package com.besy.bcsb.dto.mapper;

import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.dto.request.PeliculaSerieRequestDto;
import com.besy.bcsb.dto.response.PeliculaSerieResponseDto;
import com.besy.bcsb.dto.FechasDto;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface IPeliculaSerieMapper {

    PeliculaSerie mapToEntity(PeliculaSerieRequestDto dto);

    PeliculaSerieResponseDto mapToDto(PeliculaSerie entity);

    Map<String, Object> mapFechasDtoToParams(FechasDto fechasDto);
}
