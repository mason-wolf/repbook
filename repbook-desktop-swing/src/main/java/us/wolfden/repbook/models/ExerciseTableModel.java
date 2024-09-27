package us.wolfden.repbook.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ExerciseTableModel extends AbstractTableModel {
    private final String[] columnNames = { "Exercise", "Sets", "Reps"};
    private List<Exercise> exercises;

    public ExerciseTableModel(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public int getRowCount() {
        return exercises.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exercise exercise = exercises.get(rowIndex);
        switch (columnIndex) {
            case 0: return exercise.getName();
            case 1: return exercise.getSets();
            case 2: return exercise.getReps();
            default: return null;
        }
    }
}

