package us.wolfden.repbook.models;

import us.wolfden.repbook.services.WorkoutServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewModel {
    private List<Workout> workouts;
    private WorkoutTableModel tableModel;
    private WorkoutServiceImpl workoutService = new WorkoutServiceImpl();

    public WorkoutViewModel() {
        this.workouts = workoutService.getWorkouts();
        this.tableModel = new WorkoutTableModel(workouts);
    }

    public WorkoutTableModel getTableModel() {
        return tableModel;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        tableModel.fireTableDataChanged();
    }
}
