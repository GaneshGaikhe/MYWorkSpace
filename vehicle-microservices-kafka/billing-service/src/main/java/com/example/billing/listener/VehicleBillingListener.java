package com.example.billing.listener; import org.springframework.kafka.annotation.KafkaListener; import org.springframework.stereotype.Component;
@Component public class VehicleBillingListener {
    @KafkaListener(topics = "vehicle.events", groupId = "billing-group", containerFactory = "kafkaListenerContainerFactory") public void consume(com.example.vehicle.dto.VehicleEvent event){
        // simulate billing action
        System.out.println("[billing-service] charging for vehicle: " + event.getVehicleId());
    }
}
