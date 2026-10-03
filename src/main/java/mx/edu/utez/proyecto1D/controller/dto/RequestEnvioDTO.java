package mx.edu.utez.proyecto1D.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEnvioDTO {


    @NotBlank(message = "El codigo postal es obligatorio ")
    private String codigoPostal;

    @DecimalMin(value = "0.1", message = "El peso debe ser al menos 0.1 kg")
    @Max(value = 50, message = "El peso no puede ser mayor a 50 kg")
    private double pesoKg;

    @Min(value = 1, message = "El largo debe ser al menos 1 cm")
    @Max(value = 150, message = "El largo no puede ser mayor a 150 cm")
    private double largoCm;

    @Min(value = 1, message = "El ancho debe ser al menos 1 cm")
    @Max(value = 150, message = "El ancho no puede ser mayor a 150 cm")
    private double anchoCm;

    @Min(value = 1, message = "El alto debe ser al menos 1 cm")
    @Max(value = 150, message = "El alto no puede ser mayor a 150 cm")
    private double altoCm;

    @NotBlank(message = "El tipo de envio es obligatorio")
    private String tipoEnvio;

    private int valorDeclarado;




}
