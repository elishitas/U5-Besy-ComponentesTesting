package com.besy.bcsb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeResponseDto {
    private Long id;
    private String nombre;
    private Integer edad;
    private Float peso;
    private String historia;
}
