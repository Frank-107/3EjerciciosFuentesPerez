package mx.edu.utez.proyecto1D.controller.service;

import mx.edu.utez.proyecto1D.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto1D.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.proyecto1D.controller.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO calcularHospedaje(RequestHospedajeDTO dto) {

        if (dto.getNumeroNoches() > 30) {
            throw new CustomBadRequestException("El número de noches no puede ser mayor a 30");
        }

        double costoPorNoche;
        int capacidadMaxima;

        switch (dto.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL" -> {
                costoPorNoche = 700.0;
                capacidadMaxima = 1;
            }
            case "DOBLE" -> {
                costoPorNoche = 1100.0;
                capacidadMaxima = 2;
            }
            case "SUITE" -> {
                costoPorNoche = 1800.0;
                capacidadMaxima = 4;
            }
            default -> throw new CustomBadRequestException("Tipo de habitación no válido");
        }

        if (dto.getNumeroHuespedes() > capacidadMaxima) {
            throw new CustomBadRequestException(
                    "La habitación " + dto.getTipoHabitacion() + " solo permite un máximo de " + capacidadMaxima + " huésped(es)"
            );
        }

        double costoHospedajeBase = costoPorNoche * dto.getNumeroNoches();

        double ajusteTemporada = 0.0;
        switch (dto.getTemporada().toUpperCase()) {
            case "BAJA" -> ajusteTemporada = - (costoHospedajeBase * 0.10);
            case "REGULAR" -> ajusteTemporada = 0.0;
            case "ALTA" -> ajusteTemporada = costoHospedajeBase * 0.25;
            default -> throw new CustomBadRequestException("Temporada no válida");
        }

        double descuentoEstanciaLarga = 0.0;
        if (dto.getNumeroNoches() >= 7) {
            descuentoEstanciaLarga = costoHospedajeBase * 0.08;
        }

        double costoHospedajeFinal = costoHospedajeBase + ajusteTemporada - descuentoEstanciaLarga;

        double costoDesayuno = 0.0;
        if (dto.getIncluyeDesayuno()) {
            costoDesayuno = dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (dto.getIncluyeEstacionamiento()) {
            costoEstacionamiento = dto.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedajeFinal + costoDesayuno + costoEstacionamiento;

        double impuesto = subtotal * 0.04;

        double total = subtotal + impuesto;

        ResponseHospedajeDTO response = new ResponseHospedajeDTO();
        response.setPrecio(total);
        response.setMensaje("Cotización de hospedaje calculada exitosamente para " + dto.getNombreHuesped());

        return response;
    }
}
