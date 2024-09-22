package us.wolfden.repbook;


import us.wolfden.repbook.repositories.WorkoutDbRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        WorkoutDbRepositoryImpl workoutDb = new WorkoutDbRepositoryImpl();
        workoutDb.createTable();
        System.out.println("Hello from cli");
    }
}