package com.besy.bcsb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaSerieRequestDto {

    private Long id;

    @Size(message = "El título no puede ser mayor a 20 carácteres.", max = 20)
    @NotBlank(message = "El título no puede estar vacío.")
    private String titulo;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate fechaDeCreacion;

    @Min(value=1, message="El valor no puede ser menor a 1")
    @Max(value=5,message="El valor no puede ser mayor a 5")
    @NotNull(message = "La calificación no puede ser nula.")
    private Integer calificacion;

}
