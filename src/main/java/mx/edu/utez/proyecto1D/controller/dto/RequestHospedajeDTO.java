package mx.edu.utez.proyecto1D.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class RequestHospedajeDTO {
    @NotBlank
    private String nombreHuesped;
    @NotBlank
    @Pattern(regexp="^(INDIVIDUAL|DOBLE|SUITE)$", message = "El tipo de habitación no es válido")
    private String tipoHabitacion;
    @Positive
    @Max(value = 30, message = "El número de noches no puede ser mayor a 30")
    private int numeroNoches;
    @Positive
    private int numeroHuespedes;
    @NotBlank
    @Pattern(regexp = "^(ALTA|BAJA|REGULAR)$", message = "temporada no valida")
    private String temporada;
    @NotNull
    private Boolean incluyeDesayuno;
    @NotNull
    private  Boolean incluyeEstacionamiento;

}
