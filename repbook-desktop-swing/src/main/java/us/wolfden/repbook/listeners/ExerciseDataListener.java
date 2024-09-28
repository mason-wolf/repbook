package us.wolfden.repbook.listeners;

import us.wolfden.repbook.models.Exercise;

public interface ExerciseDataListener {
    void onExerciseDataAdded(Exercise exercise);
    void onExerciseDataEdited(Exercise exercise);
    void onExerciseDataDeleted(Exercise exercise);
}
