package it.unicam.cs.ids.lg;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        // Menu
        ArrayList<Food> menu = new ArrayList<>();
        menu.add(new Food(0, " Spaghetti alla Carbonara "));
        menu.add(new Food(1, " Tonnarelli Cacio e Pepe  "));
        menu.add(new Food(2, " Bucatini alla Amatriciana"));
        menu.add(new Food(3, " Abbacchio al Forno       "));
        menu.add(new Food(4, " Saltimbocca alla Romana  "));
        menu.add(new Food(5, " Coda alla Vaccinara      "));
        menu.add(new Food(6, " Insalta Mista            "));
        menu.add(new Food(7, " Carciofo alla Romana     "));
        menu.add(new Food(8, " Patate al Forno          "));
        menu.add(new Food(9, " Tiramisu                 "));
        menu.add(new Food(10, "Caffe                    "));

        OrderManager manager = new OrderManager(menu);
        manager.promptHome();
    }
}