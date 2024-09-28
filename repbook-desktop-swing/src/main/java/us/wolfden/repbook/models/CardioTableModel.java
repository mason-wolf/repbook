package us.wolfden.repbook.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CardioTableModel extends AbstractTableModel {
    private final String[] columnNames = { "Name", "Distance", "Time"};
    private List<Cardio> cardioList;

    public CardioTableModel(List<Cardio> cardio) {
        this.cardioList = cardio;
    }

    @Override
    public int getRowCount() {
        return cardioList.size();
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
        Cardio cardio = cardioList.get(rowIndex);
        switch (columnIndex) {
            case 0: return cardio.getName();
            case 1: return cardio.getDistance();
            case 2: return cardio.getTime();
            default: return null;
        }
    }
}

