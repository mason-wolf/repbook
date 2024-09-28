package us.wolfden.repbook;


import us.wolfden.repbook.models.WorkoutViewModel;
import us.wolfden.repbook.repositories.ExerciseDbRepositoryImpl;
import us.wolfden.repbook.repositories.WorkoutDbRepositoryImpl;
import us.wolfden.repbook.views.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("repbook");
            WorkoutViewModel workoutViewModel = new WorkoutViewModel();
            MainView mainView = new MainView(mainFrame, workoutViewModel);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });

    }
}