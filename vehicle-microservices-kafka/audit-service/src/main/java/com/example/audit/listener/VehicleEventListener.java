package com.example.audit.listener; import com.example.audit.model.AuditRecord; import com.example.audit.repo.AuditRepository; import org.springframework.kafka.annotation.KafkaListener; import org.springframework.stereotype.Component; import java.util.Map;
@Component public class VehicleEventListener {
    private final AuditRepository repo;
    public VehicleEventListener(AuditRepository repo){ this.repo = repo; }
    @KafkaListener(topics = "vehicle.events", groupId = "audit-group", containerFactory = "kafkaListenerContainerFactory") public void consume(com.example.vehicle.dto.VehicleEvent event){
        // persist audit
        AuditRecord r = new AuditRecord(event.getEventId(), event.getVehicleId(), event.getModel(), event.getMake(), event.getPerformedBy());
        repo.save(r);
        System.out.println("[audit-service] consumed event: " + event.getEventId());
    }
}
