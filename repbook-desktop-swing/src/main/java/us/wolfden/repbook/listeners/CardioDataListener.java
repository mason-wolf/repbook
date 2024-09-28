package us.wolfden.repbook.listeners;

import us.wolfden.repbook.models.Cardio;

public interface CardioDataListener {
    void onCardioDataAdded(Cardio cardio);
    void onCardioDataEdited(Cardio cardio);
    void onCardioDataDeleted(Cardio cardio);
}
