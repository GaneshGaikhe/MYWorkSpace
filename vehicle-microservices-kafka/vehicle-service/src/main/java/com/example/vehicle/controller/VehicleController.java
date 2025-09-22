package com.example.vehicle.controller;
import com.example.vehicle.dto.VehicleEvent; import com.example.vehicle.service.VehicleProducer; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.UUID; import java.util.Map;
@RestController @RequestMapping("/vehicles") public class VehicleController {
    private final VehicleProducer producer;
    public VehicleController(VehicleProducer producer){ this.producer = producer; }
    @PostMapping("/add") public ResponseEntity<?> add(@RequestBody Map<String,String> body){
        String vehicleId = UUID.randomUUID().toString();
        VehicleEvent event = new VehicleEvent(UUID.randomUUID().toString(), vehicleId, body.getOrDefault("model","unknown"), body.getOrDefault("make","unknown"), body.getOrDefault("performedBy","system"));
        producer.publishVehicleCreated(event);
        return ResponseEntity.ok(Map.of("vehicleId", vehicleId, "status","published"));
    }
    @GetMapping("/view") public ResponseEntity<?> view(){ return ResponseEntity.ok(Map.of("status","vehicle-service OK")); }
}
