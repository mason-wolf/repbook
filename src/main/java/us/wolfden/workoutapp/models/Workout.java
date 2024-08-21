package us.wolfden.workoutapp.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Builder
public class Workout {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String type;
    private String date;  
    
    @Getter
    @Builder.Default
    private List<Exercise> exercises = new ArrayList<>();
    
    @Getter
    @Builder.Default 
    private List<Cardio> cardio = new ArrayList<>();

    public Workout(
        String id, 
        String name, 
        String type, 
        String date, 
        List<Exercise> exercises,
        List<Cardio> cardio) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.exercises = exercises;
        this.cardio = cardio;
    }

    public Workout() {}
    
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
    
    public void addCardio(Cardio cardio) {
        this.cardio.add(cardio);
    }

}
