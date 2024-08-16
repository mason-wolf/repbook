package us.wolfden.workoutapp.models;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class Exercise {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private int sets;
    private int reps;
    private double weight;

    public Exercise(String id, String name, int sets, int reps, double weight) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
    public Exercise() {}
}
