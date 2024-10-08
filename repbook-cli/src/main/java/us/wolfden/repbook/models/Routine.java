package us.wolfden.repbook.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class Routine {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    @Getter
    @Setter
    private List<Exercise> exercises = new ArrayList<Exercise>();

    public Routine(String id, String name, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Routine() {
    }
}
