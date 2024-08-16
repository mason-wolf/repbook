package us.wolfden.workoutapp.menus;

import java.util.List;

public interface Menu {
    List<String> options();
    void display();
    void handleSelection();
}
