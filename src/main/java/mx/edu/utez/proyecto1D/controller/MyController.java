package mx.edu.utez.proyecto1D.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.dto.RequestBodyDTO;
import mx.edu.utez.proyecto1D.controller.dto.RequestEnvioDTO;
import mx.edu.utez.proyecto1D.controller.dto.ResponseEnvioDTO;
import mx.edu.utez.proyecto1D.controller.service.MyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services")
public class MyController {

    @PostMapping("/envio")
    public ResponseEntity<ResponseEnvioDTO> envio(@RequestBody @Valid RequestEnvioDTO payload){
        MyService myService = new MyService();
        return ResponseEntity.status(200).body(myService.calcularEnvio(payload));
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
