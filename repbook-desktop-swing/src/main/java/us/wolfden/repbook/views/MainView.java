package us.wolfden.repbook.views;

import us.wolfden.repbook.listeners.WorkoutDataListener;
import us.wolfden.repbook.models.Exercise;
import us.wolfden.repbook.models.Workout;
import us.wolfden.repbook.models.WorkoutViewModel;
import us.wolfden.repbook.services.WorkoutServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class MainView implements WorkoutDataListener {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JTable workoutsTable;
    private JButton addWorkoutButton;
    private WorkoutViewModel viewModel;

    public MainView(JFrame frame, WorkoutViewModel viewModel) {
        frame.setContentPane(rootPanel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        this.viewModel = viewModel;
        handleViewWorkouts();
        handleViewWorkout();
        handleAddWorkout();
    }

    private void handleViewWorkout() {
        workoutsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JFrame viewWorkoutFrame = new JFrame("Workout");
                    ViewWorkoutView viewWorkoutView = new ViewWorkoutView(viewWorkoutFrame);
                    viewWorkoutFrame.setVisible(true);
                    int row = workoutsTable.getSelectedRow();
                    if (row != -1) {
//                        String exerciseName = (String) workoutsTable.getValueAt(row, 0);
//                        Optional<Exercise> optionalExercise = getExerciseByName(exerciseName);
//                        if (optionalExercise.isPresent()) {
//                            Exercise exercise = optionalExercise.get();
//                            JFrame addExerciseFrame = new JFrame("Edit Exercise");
//                            AddExerciseView addExerciseView = new AddExerciseView(exercise, AddWorkoutView.this, addExerciseFrame);
//                            addExerciseFrame.setVisible(true);
//                        }
                    }
                }
            }
        });
    }

    private void handleAddWorkout() {
        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addWorkoutFrame = new JFrame("Add Workout");
                AddWorkoutView addWorkoutView = new AddWorkoutView(MainView.this, addWorkoutFrame);
                addWorkoutFrame.setVisible(true);
            }
        });
    }
    private void handleViewWorkouts() {
        workoutsTable.setModel(viewModel.getTableModel());
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        rootPanel.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Workouts", panel1);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        workoutsTable = new JTable();
        scrollPane1.setViewportView(workoutsTable);
        addWorkoutButton = new JButton();
        addWorkoutButton.setText("Add Workout");
        panel1.add(addWorkoutButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Routines", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Reports", panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Profile", panel4);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    @Override
    public void onWorkoutDataAdded(Workout workout) {
        WorkoutViewModel viewModel = new WorkoutViewModel();
        workoutsTable.setModel(viewModel.getTableModel());
    }
}
