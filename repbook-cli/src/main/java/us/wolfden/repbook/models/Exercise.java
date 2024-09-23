package us.wolfden.repbook.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Exercise {
    private int id;
    private String name;
    private int sets;
    private int reps;
    private double weight;
    private int workoutId;

    public Exercise(int id, String name, int sets, int reps, double weight, int workoutId) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.workoutId = workoutId;
    }

    public Exercise() {
    }
}
