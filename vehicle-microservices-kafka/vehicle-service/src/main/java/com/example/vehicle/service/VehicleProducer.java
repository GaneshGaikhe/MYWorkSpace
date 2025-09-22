package com.example.vehicle.service;
import com.example.vehicle.dto.VehicleEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service public class VehicleProducer {
    private final KafkaTemplate<String, VehicleEvent> kafkaTemplate;
    public static final String TOPIC = "vehicle.events";
    public VehicleProducer(KafkaTemplate<String, VehicleEvent> kafkaTemplate){ this.kafkaTemplate = kafkaTemplate; }
    public void publishVehicleCreated(VehicleEvent event){ kafkaTemplate.send(TOPIC, event.getEventId(), event); }
}
