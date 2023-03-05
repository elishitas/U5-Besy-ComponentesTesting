package com.besy.bcsb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequestDto {
    private Long id;

    @Size(message = "El nombre no puede ser mayor a 20 carácteres.", max = 20)
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;

    @NotNull(message = "La edad no puede ser nula.")
    private Integer edad;

    @NotNull(message = "El peso no puede ser nula.")
    private Float peso;

    @NotBlank(message = "La historia no puede estar vacía.")
    private String historia;
}
