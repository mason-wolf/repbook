package us.wolfden.workoutapp.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Cardio {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String time;
    private double distance;
    
    public Cardio(String id, String name, String time, double distance) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.distance = distance;
    }

    public Cardio() {}
}
