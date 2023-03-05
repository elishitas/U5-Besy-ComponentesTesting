package com.besy.bcsb.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GeneroRequestDto {
    
    @Size(message = "El nombre no puede ser mayor a 30 carácteres.", max = 30)
    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String nombre;
}
