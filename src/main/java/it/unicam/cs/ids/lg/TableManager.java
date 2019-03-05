package it.unicam.cs.ids.lg;

import java.util.ArrayList;

public class TableManager {
    private ArrayList<Table> tables;

    public TableManager() {
        tables = new ArrayList<>();
    }

    public TableManager(ArrayList<Table> tables) {
        this.tables = tables;
    }

    // Set restaurant's tables
    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    // Return restaurant's tables
    public ArrayList<Table> getTables() {
        return tables;
    }
}