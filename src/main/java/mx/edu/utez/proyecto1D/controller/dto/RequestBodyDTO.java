package mx.edu.utez.proyecto1D.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=3, message ="la edad es de al menos 3 caracteres")
    private String nombre;
    @Min(value=18, message = "La edad debe ser al menos 18"   )
    private int edad;
    @NotBlank(message = "El cuerpo del correo es obligatorio")
    @Email(message = "El correo no es valido ")
    private String correo;
    @NotBlank(message = "El curp es obligatorio")
    @Pattern(
            regexp = "^[A-Z]{4}\\d{6}[HM][A-Z]{5}[A-Z\\d]\\d$",
            message = "La CURP no tiene un formato válido"
    )    private String curp;





}
