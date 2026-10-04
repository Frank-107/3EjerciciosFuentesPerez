package mx.edu.utez.proyecto1D.controller.dto;

import ch.qos.logback.core.boolex.EvaluationException;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;
    @Positive(message = "La edad del conductor debe ser un número positivo")
    @Min(value = 18, message = "La edad del conductor debe ser al menos 18 años")
    private int edadConductor;
    @Pattern(regexp = ("^(SEDAN|SUV|COMPACTO|CAMIONETA)$"), message = "El tipo de vehículo no es válido")
    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    private String tipoVehiculo;
    @Positive
    @Max(value=30, message = "La cantidad de días de renta no puede ser mayor a 30")
    private int diasRenta;
    @Positive
    @Max(value=5000)
    private int kilometrosEstimados;
    private boolean seguroCompleto;




}
