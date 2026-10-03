package mx.edu.utez.proyecto1D.controller.service;

import mx.edu.utez.proyecto1D.controller.dto.RequestEnvioDTO;
import mx.edu.utez.proyecto1D.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.proyecto1D.controller.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public ResponseEnvioDTO calcularEnvio(RequestEnvioDTO data){
        ResponseEnvioDTO respuesta = new ResponseEnvioDTO();
        double costo = 80.0 + (12.0*data.getPesoKg());
        double volumen = data.getAltoCm() * data.getAnchoCm() * data.getLargoCm();
        if(volumen>50000){
            costo += 100.0; // Costo adicional por volumen excesivo
        }
        if (volumen>1000000) {
            throw new CustomBadRequestException("El volumen es demasiado grande para enviar");
        }

        if(
                !data.getTipoEnvio().equals("EXPRESS")&&
                !data.getTipoEnvio().equals("ESTANDAR")&&
                !data.getTipoEnvio().equals("MISMO_DIA")
        ){
            throw new CustomBadRequestException("Hola, no jala ese tipo de envio pa");
        }
        if(data.getTipoEnvio().equals("EXPRESS")){
            costo = costo*1.40;
        }
        if (data.getTipoEnvio().equals("MISMO_DIA")){
            costo = costo*1.70;
        }

        if(data.getValorDeclarado()>10000){
            costo = costo+ (0.02*data.getValorDeclarado());
            respuesta.setMensaje("Se le puso un seguro por el valor agregado ");
        }
        respuesta.setCostoEnvio(costo);
        respuesta.setVolumen(volumen);

        return respuesta;



    }


}
