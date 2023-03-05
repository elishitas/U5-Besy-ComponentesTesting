package com.besy.bcsb.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaSerieResponseDto {

    private Long id;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private Byte calificacion;
}
