package mx.edu.utez.proyecto1D.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.proyecto1D.controller.dto.*;
import mx.edu.utez.proyecto1D.controller.service.HospedajeService;
import mx.edu.utez.proyecto1D.controller.service.MyService;
import mx.edu.utez.proyecto1D.controller.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services")
@RequiredArgsConstructor
public class MyController {
    final MyService myService;
    final RentaService rentaService;
    final HospedajeService hospedajeService;

    @PostMapping("/hospedaje")
    public ResponseEntity<ResponseHospedajeDTO> hospedaje(@RequestBody @Valid RequestHospedajeDTO payload){
        return ResponseEntity.status(200).body(hospedajeService.calcularHospedaje(payload));
    }


    @PostMapping("/envio")
    public ResponseEntity<ResponseEnvioDTO> envio(@RequestBody @Valid RequestEnvioDTO payload){
        return ResponseEntity.status(200).body(myService.calcularEnvio(payload));
    }

    @PostMapping("/renta")
    public ResponseEntity<ResponseRentaDTO> renta(@RequestBody @Valid RequestRentaDTO payload){

        return ResponseEntity.status(200).body(rentaService.calcularRenta(payload));
    }
    @GetMapping
    public String miPrimerServicio(){
        System.out.println("Hello world");
        return "Hello World";
    }
    @GetMapping("/2doServicio")
    public String servicio2(){
        return "Este es mi segundo servicio";
    }
    @PostMapping
    public String servicio3(){
        return "Tercer servicio";
    }

    @GetMapping("/path/{id}")
    public String pathVariable(@PathVariable String id){

        return "El id es: "+ id;
    }
    //REsponse entitty es una calse que em permite personalizar la respuesta que se le manda al cliente
    @PostMapping("/req-body")
    public ResponseEntity<RequestBodyDTO> requestBody(@RequestBody @Valid RequestBodyDTO paylod){
        System.out.println(paylod.getNombre());
        System.out.println(paylod.getEdad());
        System.out.println(paylod.getCorreo());
        return ResponseEntity.status(201).body(paylod);
    }
}
