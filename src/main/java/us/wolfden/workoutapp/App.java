package us.wolfden.workoutapp;
import us.wolfden.workoutapp.menus.MainMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}
