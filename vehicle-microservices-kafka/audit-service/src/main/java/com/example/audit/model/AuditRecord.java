package com.example.audit.model; import javax.persistence.*; import java.time.Instant;
@Entity public class AuditRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String eventId; private String vehicleId; private String model; private String make; private String performedBy; private Instant createdAt = Instant.now();
    public AuditRecord(){} public AuditRecord(String eventId, String vehicleId, String model, String make, String performedBy){ this.eventId=eventId; this.vehicleId=vehicleId; this.model=model; this.make=make; this.performedBy=performedBy; }
    // getters/setters
    public Long getId(){return id;} public String getEventId(){return eventId;} public String getVehicleId(){return vehicleId;} public String getModel(){return model;} public String getMake(){return make;} public String getPerformedBy(){return performedBy;} public Instant getCreatedAt(){return createdAt;}
    public void setId(Long id){this.id=id;} public void setEventId(String e){this.eventId=e;} public void setVehicleId(String v){this.vehicleId=v;} public void setModel(String m){this.model=m;} public void setMake(String m){this.make=m;} public void setPerformedBy(String p){this.performedBy=p;} public void setCreatedAt(Instant t){this.createdAt=t;}
}
