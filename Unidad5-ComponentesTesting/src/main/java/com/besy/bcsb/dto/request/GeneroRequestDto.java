package com.besy.bcsb.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GeneroRequestDto {
    
    @Size(message = "El nombre no puede ser mayor a 20 carácteres.", max = 20)
    @NotBlank(message = "El nombre no puede ser vacío.")
    private String nombre;
}
