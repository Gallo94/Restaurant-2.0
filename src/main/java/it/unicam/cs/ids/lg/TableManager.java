package it.unicam.cs.ids.lg;

import java.util.ArrayList;

public class TableManager
{
    private ArrayList<Table> tables;

    public TableManager()
    {
        tables = new ArrayList<>();
    }

    public TableManager(ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    public void setTables(ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    public ArrayList<Table> getTables()
    {
        return tables;
    }
}