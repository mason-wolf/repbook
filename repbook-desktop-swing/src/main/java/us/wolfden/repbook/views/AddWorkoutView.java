package us.wolfden.repbook.views;

import us.wolfden.repbook.listeners.CardioDataListener;
import us.wolfden.repbook.listeners.ExerciseDataListener;
import us.wolfden.repbook.listeners.WorkoutDataListener;
import us.wolfden.repbook.models.*;
import us.wolfden.repbook.services.WorkoutServiceImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class AddWorkoutView implements ExerciseDataListener, CardioDataListener {
    private JPanel rootPanel;
    private JTextField workoutName;
    private JComboBox<String> workoutType;
    private JTextField workoutDate;
    private JButton addExerciseBtn;
    private JButton selectRoutineBtn;
    private JButton addCardioBtn;
    private JButton addWorkoutButton;
    private JPanel workoutDetailsPanel;
    private JTable exerciseTable;
    private JTable cardioTable;
    private final ArrayList<Exercise> exerciseList = new ArrayList<>();
    private final ArrayList<Cardio> cardioList = new ArrayList<>();
    private final WorkoutServiceImpl workoutService;
    private boolean validWorkout;
    private final Workout workout = new Workout();
    private final JFrame frame;
    private final WorkoutDataListener listener;

    public AddWorkoutView(WorkoutDataListener listener, JFrame frame) {
        this.listener = listener;
        workoutService = new WorkoutServiceImpl();
        this.frame = frame;
        frame.setContentPane(rootPanel);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        handleAddWorkout();
        handleSelectWorkoutType();
        handleAddDate();
        handleAddExercise();
        handleEditExercise();
        handleEditCardio();
        this.handleAddCardio();
    }

    private void handleAddCardio() {
        addCardioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addCardioFrame = new JFrame("Add Cardio");
                AddCardioView addCardioView = new AddCardioView(null, addCardioFrame, AddWorkoutView.this);
                addCardioFrame.setVisible(true);
            }
        });
    }

    private void handleEditCardio() {
        cardioTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = cardioTable.getSelectedRow();
                    if (row != -1) {
                        String cardioName = (String) cardioTable.getValueAt(row, 0);
                        Optional<Cardio> optionalCardio = getCardioByName(cardioName);
                        if (optionalCardio.isPresent()) {
                            Cardio cardio = optionalCardio.get();
                            JFrame addCardioFrame = new JFrame("Edit Cardio");
                            AddCardioView addCardioView = new AddCardioView(cardio, addCardioFrame, AddWorkoutView.this);
                            addCardioFrame.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private void handleEditExercise() {
        exerciseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = exerciseTable.getSelectedRow();
                    if (row != -1) {
                        String exerciseName = (String) exerciseTable.getValueAt(row, 0);
                        Optional<Exercise> optionalExercise = getExerciseByName(exerciseName);
                        if (optionalExercise.isPresent()) {
                            Exercise exercise = optionalExercise.get();
                            JFrame addExerciseFrame = new JFrame("Edit Exercise");
                            AddExerciseView addExerciseView = new AddExerciseView(exercise, AddWorkoutView.this, addExerciseFrame);
                            addExerciseFrame.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private Optional<Cardio> getCardioByName(String name) {
        return cardioList.stream()
                .filter(cardio -> cardio.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    private Optional<Exercise> getExerciseByName(String name) {
        return exerciseList.stream()
                .filter(exercise -> exercise.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    private void handleAddExercise() {
        addExerciseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addExerciseFrame = new JFrame("Add Exercise");
                AddExerciseView addExerciseView = new AddExerciseView(null, AddWorkoutView.this, addExerciseFrame);
                addExerciseFrame.setVisible(true);
            }
        });
    }

    private void handleSelectWorkoutType() {
        String[] options = {"Strength", "Cardio"};

        for (String option : options) {
            workoutType.addItem(option);
        }

        workoutType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = (String) workoutType.getSelectedItem();
                workout.setType(selectedValue);
            }
        });
    }

    private boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private String handleAddDate() {
        return workoutDate.getText();
    }


    private void handleAddWorkout() {
        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String workName = workoutName.getText();
                String date = handleAddDate();
                if (!isValidDate(date)) {
                    validWorkout = false;
                    JOptionPane.showMessageDialog(null, "Invalid date. Must be mm/dd/yyyy format.", "Date Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    validWorkout = true;
                }
                String selectedValue = (String) workoutType.getSelectedItem();
                workout.setType(selectedValue);
                workout.setName(workName);
                workout.setDate(date);
                workout.setExercises(exerciseList);
                workout.setCardio(cardioList);
                if (validWorkout) {
                    workoutService.saveWorkout(workout);
                    listener.onWorkoutDataAdded(workout);
                    frame.dispose();
                }
            }
        });
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
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        workoutDetailsPanel = new JPanel();
        workoutDetailsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(10, 10, 10, 10), -1, -1));
        rootPanel.add(workoutDetailsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        workoutDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Workout Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Workout Name:");
        workoutDetailsPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        workoutName = new JTextField();
        workoutDetailsPanel.add(workoutName, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Type:");
        workoutDetailsPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        workoutType = new JComboBox();
        workoutDetailsPanel.add(workoutType, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Exercises:");
        workoutDetailsPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Date:");
        workoutDetailsPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        workoutDate = new JTextField();
        workoutDetailsPanel.add(workoutDate, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        workoutDetailsPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addExerciseBtn = new JButton();
        addExerciseBtn.setLabel("Add Exercise");
        addExerciseBtn.setText("Add Exercise");
        panel1.add(addExerciseBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectRoutineBtn = new JButton();
        selectRoutineBtn.setLabel("Select Routine");
        selectRoutineBtn.setText("Select Routine");
        panel1.add(selectRoutineBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addCardioBtn = new JButton();
        addCardioBtn.setText("Add Cardio");
        panel1.add(addCardioBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Cardio:");
        workoutDetailsPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addWorkoutButton = new JButton();
        addWorkoutButton.setText("Add Workout");
        workoutDetailsPanel.add(addWorkoutButton, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        workoutDetailsPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        exerciseTable = new JTable();
        scrollPane1.setViewportView(exerciseTable);
        final JScrollPane scrollPane2 = new JScrollPane();
        workoutDetailsPanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        cardioTable = new JTable();
        scrollPane2.setViewportView(cardioTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    @Override
    public void onExerciseDataAdded(Exercise exercise) {
        this.exerciseList.add(exercise);
        exerciseTable.setModel(new ExerciseTableModel(this.exerciseList));
    }

    @Override
    public void onExerciseDataEdited(Exercise exercise) {
        this.exerciseList.stream()
                .filter(e -> e.getName().equalsIgnoreCase(exercise.getName()))
                .findFirst()
                .ifPresent(e -> {
                    e.setName(exercise.getName());
                    e.setSets(exercise.getSets());
                    e.setReps(exercise.getReps());
                });
        this.exerciseTable.setModel(new ExerciseTableModel(this.exerciseList));
    }

    @Override
    public void onExerciseDataDeleted(Exercise exercise) {
        this.exerciseList.removeIf(e -> e.getName().equalsIgnoreCase(exercise.getName()));
        this.exerciseTable.setModel(new ExerciseTableModel(this.exerciseList));
    }

    @Override
    public void onCardioDataAdded(Cardio cardio) {
        this.cardioList.add(cardio);
        cardioTable.setModel(new CardioTableModel(this.cardioList));

    }

    @Override
    public void onCardioDataEdited(Cardio cardio) {
        this.cardioList.stream()
                .filter(c -> c.getName().equalsIgnoreCase(cardio.getName()))
                .findFirst()
                .ifPresent(c -> {
                    c.setName(cardio.getName());
                    c.setDistance(cardio.getDistance());
                    c.setTime(cardio.getTime());
                });
        this.cardioTable.setModel(new CardioTableModel(this.cardioList));
    }

    @Override
    public void onCardioDataDeleted(Cardio cardio) {
        this.cardioList.removeIf(c -> c.getName().equalsIgnoreCase(cardio.getName()));
        this.cardioTable.setModel(new CardioTableModel(this.cardioList));
    }
}
