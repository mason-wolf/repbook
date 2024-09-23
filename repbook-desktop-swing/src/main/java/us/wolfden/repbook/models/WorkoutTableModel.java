package us.wolfden.repbook.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class WorkoutTableModel extends AbstractTableModel {
    private final String[] columnNames = { "Date", "Title", "Type"};
    private List<Workout> workouts;

    public WorkoutTableModel(List<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public int getRowCount() {
        return workouts.size();
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
        Workout workout = workouts.get(rowIndex);
        switch (columnIndex) {
            case 0: return workout.getDate();
            case 1: return workout.getName();
            case 2: return workout.getType();
            default: return null;
        }
    }
}
