package com.example.vehicle.controller;
import com.example.vehicle.security.JwtUtil; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/vehicles") public class VehicleController {
    private final JwtUtil jwtUtil; public VehicleController(JwtUtil jwtUtil){ this.jwtUtil = jwtUtil; }
    @GetMapping("/view") public ResponseEntity<?> view(){ return ResponseEntity.ok(Map.of("status","vehicle-service OK")); }
    @PostMapping("/add") public ResponseEntity<?> add(@RequestHeader("Authorization") String auth, @RequestBody Map<String,Object> body){
        if(auth==null || !auth.startsWith("Bearer ")) return ResponseEntity.status(401).body(Map.of("error","Missing token"));
        String token = auth.substring(7);
        if(!jwtUtil.validateToken(token)) return ResponseEntity.status(401).body(Map.of("error","Invalid token"));
        var roles = jwtUtil.getRoles(token);
        if(!roles.contains("ROLE_ADMIN")) return ResponseEntity.status(403).body(Map.of("error","Forbidden"));
        return ResponseEntity.ok(Map.of("message","Vehicle added (simulated)"));
    }
}
