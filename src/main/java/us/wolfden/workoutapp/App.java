package us.wolfden.workoutapp;

import us.wolfden.workoutapp.menus.MainMenu;
import us.wolfden.workoutapp.menus.MenuManager;
import us.wolfden.workoutapp.repositories.WorkoutDbRepositoryImpl;

public class App 
{
    public static void main( String[] args )
    {
        WorkoutDbRepositoryImpl workoutDb = new WorkoutDbRepositoryImpl();
        workoutDb.createTable();
//        MenuManager.clearMenu();
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.display();
    }
}
