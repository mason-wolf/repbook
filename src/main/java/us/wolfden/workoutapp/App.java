package us.wolfden.workoutapp;

import us.wolfden.workoutapp.menus.MainMenu;
import us.wolfden.workoutapp.menus.MenuManager;

public class App 
{
    public static void main( String[] args )
    {
        MenuManager.clearMenu();
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}
