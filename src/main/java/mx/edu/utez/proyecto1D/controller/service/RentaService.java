package mx.edu.utez.proyecto1D.controller.service;

import mx.edu.utez.proyecto1D.controller.dto.RequestRentaDTO;
import mx.edu.utez.proyecto1D.controller.dto.ResponseRentaDTO;
import mx.edu.utez.proyecto1D.controller.exception.customExceptions.CustomBadRequestException;

import org.springframework.stereotype.Service;

@Service
public class RentaService {
    public ResponseRentaDTO calcularRenta(RequestRentaDTO dto){
        if ("CAMIONETA".equalsIgnoreCase(dto.getTipoVehiculo()) && dto.getEdadConductor() < 25) {
            throw new CustomBadRequestException("No se permite la renta de una CAMIONETA a conductores menores de 25 años");
        }

        double costoDiario = switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO" -> 550.0;
            case "SEDAN" -> 700.0;
            case "SUV" -> 950.0;
            case "CAMIONETA" -> 1200.0;
            default -> throw new CustomBadRequestException("Tipo de vehículo no válido");
        };

        double costoRenta = costoDiario * dto.getDiasRenta();

        int kmIncluidos = dto.getDiasRenta() * 100;
        double cargoKm = 0.0;
        if (dto.getKilometrosEstimados() > kmIncluidos) {
            cargoKm = (dto.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKm) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(dto.isSeguroCompleto())) {
            cargoSeguro = 180.0 * dto.getDiasRenta();
        }

        double descuentoRenta = 0.0;
        if (dto.getDiasRenta() >= 7) {
            descuentoRenta = costoRenta * 0.10;
        }

        double total = (costoRenta - descuentoRenta) + cargoKm + cargoEdad + cargoSeguro;

        ResponseRentaDTO response = new ResponseRentaDTO();
        response.setPrecio(total);
        response.setMensaje("Cotización calculada exitosamente para " + dto.getNombreCliente());
        return response;
    }

}
