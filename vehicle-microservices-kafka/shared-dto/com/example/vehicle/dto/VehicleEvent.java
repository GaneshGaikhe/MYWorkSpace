package com.example.vehicle.dto;
public class VehicleEvent {
    private String eventId; private String vehicleId; private String model; private String make; private String performedBy;
    public VehicleEvent(){} public String getEventId(){return eventId;} public void setEventId(String e){this.eventId=e;} public String getVehicleId(){return vehicleId;} public void setVehicleId(String v){this.vehicleId=v;} public String getModel(){return model;} public void setModel(String m){this.model=m;} public String getMake(){return make;} public void setMake(String m){this.make=m;} public String getPerformedBy(){return performedBy;} public void setPerformedBy(String p){this.performedBy=p;} }
