package it.unicam.cs.ids.lg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {

    ArrayList<Food> menu;
    TableManager tableManager;

    static Scanner scan = new Scanner(System.in);

    public Main() throws JsonParseException, JsonMappingException, IOException {
        menu = new ArrayList<>();
        menu.add(new Food(0, "Spaghetti alla Carbonara", 10.0f));
        menu.add(new Food(1, "Tonnarelli Cacio e Pepe", 8.0f));
        menu.add(new Food(2, "Bucatini alla Amatriciana", 9.0f));
        menu.add(new Food(3, "Abbacchio al Forno", 15.0f));
        menu.add(new Food(4, "Saltimbocca alla Romana", 12.0f));
        menu.add(new Food(5, "Coda alla Vaccinara", 20.0f));
        menu.add(new Food(6, "Insalta Mista", 7.0f));
        menu.add(new Food(7, "Carciofo alla Romana", 7.0f));
        menu.add(new Food(8, "Patate al Forno", 6.0f));
        menu.add(new Food(9, "Tiramisu", 8.0f));
        menu.add(new Food(10, "Caffe", 1.0f));

        // Restores previous state
        Json json = new Json();
        tableManager = json.loadTables();

        if (tableManager.getTables().size() == 0) {
            // Tables
            ArrayList<Table> tables = new ArrayList<Table>();
            tables.add(new Table(1, 2));
            tables.add(new Table(2, 2));
            tables.add(new Table(3, 2));
            tables.add(new Table(4, 4));
            tables.add(new Table(5, 4));
            tables.add(new Table(6, 8));

            tableManager.setTables(tables);
        }
    }

    // Show home's interface
    public void promptHome() {
        int choice = 0;

        do {
            System.out.println();
            System.out.format("+====================+%n");
            System.out.format("|        HOME        |%n");
            System.out.format("+====================+%n");
            System.out.format("|  1    MENU         |%n");
            System.out.format("|  2    NEW ORDER    |%n");
            System.out.format("|  3    ORDERS       |%n");
            System.out.format("|  4    FREE TABLE   |%n");
            System.out.format("|  5    HISTORY      |%n");
            System.out.format("|  6    EXIT         |%n");
            System.out.format("+====================+%n");
            System.out.print("Insert number [1-6]: ");

            try {
                do {
                    choice = Integer.parseInt(scan.nextLine());

                    switch (choice) {
                    case 1:
                        printMenu();
                        break;
                    case 2:
                        promptNewOrder();
                        break;
                    case 3:
                        promptTableOrder();
                        break;
                    case 4:
                        promptFreeTable();
                        break;
                    case 5:
                        history();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Wrong option");
                    }
                } while (choice < 1 || choice > 6);
            } catch (NumberFormatException e) {
                System.out.println("Character not allowed");
            }
        } while (choice != 6);
    }

    // Print restaurant's menu
    public void printMenu() {
        String leftAlignFormat = "| %-2d | %-30s |%n";

        System.out.println();
        System.out.format("+=====================================+%n");
        System.out.format("|                 MENU                |%n");
        System.out.format("+=====================================+%n");

        for (Food food : menu) {
            System.out.format(leftAlignFormat, food.getId(), food.getDescription());
        }
        System.out.format("+=====================================+%n");
        System.out.println();
    }

    // Show table's status
    public Table promptSelectTable() {
        int numTables = tableManager.getTables().size();

        String leftAlignFormat = "| %-5s %-2d  |  %-4s  |%n";

        System.out.println();
        System.out.format("+====================+%n");
        System.out.format("|       TABLES       |%n");
        System.out.format("+====================+%n");

        for (Table table : tableManager.getTables())
            System.out.format(leftAlignFormat, "Table", table.getId(), (table.getOrder() == null ? "Free" : "Busy"));

        System.out.format("+====================+%n");
        System.out.print("Select table [1-" + numTables + "]: ");

        int choice = 0;
        do {
            try {

                String nextLine = scan.nextLine();
                choice = Integer.parseInt(nextLine);

            } catch (NumberFormatException e) {
                System.out.println("Character not allowed");
            }
        } while ((choice < 1 || choice > numTables));

        return tableManager.getTables().get(choice - 1);
    }

    // Show options to add/delete/confirm order
    public void promptNewOrder() {
        Table table = promptSelectTable();

        if (!table.isFree()) {
            System.out.println("Table busy!");
            return;
        }

        Order order = new Order();
        int choice = 0;
        do {
            clearScreen();
            printMenu();
            System.out.println();
            System.out.println("+====================+");
            System.out.println("|        ORDER       |");
            System.out.println("+====================+");
            System.out.println("|  1    ADD DISH     |");
            System.out.println("|  2    DEL DISH     |");
            System.out.println("|  3    CONFIRM      |");
            System.out.println("|  4    BACK         |");
            System.out.println("+====================+");
            System.out.println();
            printOrder(order);
            System.out.print("Insert number [1-4]: ");

            try {
                do {
                    choice = Integer.parseInt(scan.nextLine());
                    switch (choice) {
                    case 1:
                        promptAddDish(order);
                        order.calculatePrice();
                        break;
                    case 2:
                        promptRemoveDish(order);
                        order.calculatePrice();
                        break;
                    case 3:
                        if (order.getDishes().isEmpty()) {
                            System.out.println("Order has no dishes");
                            choice = 0;
                        } else {
                            table.setOrder(order);
                            try {
                                new Json().saveTables(tableManager);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Option wrongs");
                    }
                } while (choice < 1 || choice > 4);
            } catch (NumberFormatException e) {
                System.out.println("Character not allowed");
            }
        } while (choice != 4 && choice != 3);
    }

    // Add dish to order
    public void promptAddDish(Order order) {

        System.out.println();
        System.out.print("ID : ");
        int choiceId = Integer.parseInt(scan.nextLine());
        System.out.print("QTY: ");
        int choiceQta = Integer.parseInt(scan.nextLine());

        if ((choiceId >= 0 && choiceId <= menu.size() - 1) && choiceQta > 0) {
            if (order.hasDish(choiceId)) {
                HashMap<Integer, Dish> map = order.getDishes();
                Dish dish = map.get(choiceId);
                dish.setCount(dish.getCount() + choiceQta);
            } else {
                order.addDish(choiceId, new Dish(choiceQta, menu.get(choiceId)), choiceQta);
            }
        } else {
            System.out.println("Dish wrongs");
        }
    }

    // Remove dish from order
    public void promptRemoveDish(Order order) {

        System.out.println();
        System.out.print("ID : ");
        int choiceId = Integer.parseInt(scan.nextLine());
        System.out.print("QTY: ");
        int choiceQta = Integer.parseInt(scan.nextLine());

        if ((choiceId >= 0 && choiceId <= menu.size() - 1) && choiceQta > 0) {
            if (order.hasDish(choiceId)) {
                HashMap<Integer, Dish> map = order.getDishes();
                Dish dish = map.get(choiceId);

                if (dish.getCount() <= choiceQta) {
                    order.removeDish(choiceId, choiceQta);
                } else {
                    dish.setCount(dish.getCount() - choiceQta);
                }
            }
        }
    }

    // Print dish in order
    public void printOrder(Order order) {
        String leftAlignFormat = "| %-2d | %-3d | %-30s|%n";

        System.out.format("+==========================================+%n");
        System.out.format("| ID | QTY |          DESCRIPTION          |%n");
        System.out.format("+==========================================+%n");

        for (Dish dish : order.getDishes().values()) {
            System.out.format(leftAlignFormat, dish.getFood().getId(), dish.getCount(),
                    dish.getFood().getDescription());
            System.out.format("+------------------------------------------+%n");
        }
        System.out.println();
    }

    // Show all orders confirmed
    public void promptTableOrder() {
        Table table = promptSelectTable();

        if (table.isFree()) {
            System.out.println("Table has got any order!");
            return;
        }

        promptOrderDetails(table.getOrder());

        try {
            new Json().saveTables(tableManager);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Choose the table to be released
    public void promptFreeTable() {
        Table table = promptSelectTable();
        if (table.isFree()) {
            System.out.println("Table is already free");
            return;
        }

        table.free();
        try {
            new Json().saveTables(tableManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Table " + table.getId() + " is released!");
    }

    // Show prepared order's history
    public void history() {
        ArrayList<Table> tables = tableManager.getTables();
        for (Table table : tables) {
            System.out.println();
            System.out.println("+==============+");
            System.out.println("|| TABLE N." + table.getId() + "  ||");
            System.out.println("+==============+");

            if (table.getPreviousOrders() == null) {
                System.out.println();
                continue;
            }

            ArrayList<Order> orders = table.getPreviousOrders();
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("+-----------+");
                System.out.println("| ORDER #" + i + "  |");
                System.out.println("+-----------+");
                Order order = orders.get(i);
                HashMap<Integer, Dish> dishes = order.getDishes();
                for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
                    Integer key = entry.getKey();
                    Dish value = entry.getValue();
                    System.out.println("|- " + value.getCount() + " " + value.getFood().getDescription() + "  €"
                            + value.getFood().getPrice());
                }
                System.out.println("|");
                System.out.println("| TOTAL: " + "€" + order.getPrice());
                System.out.println("- - - - - - - - - - - - - - -");
            }
        }
    }

    // Show order's details
    public void promptOrderDetails(Order order) {
        String leftAlignFormat = "| %-2d | %-3d | %-28s  %-1s |%n";

        do {
            System.out.println();
            System.out.println("LEGEND:");
            System.out.println(" - = cooking | x = ready");
            System.out.println();
            System.out.format("+=============================================+%n");
            System.out.format("| ID | QTY |            DESCRIPTION           |%n");
            System.out.format("+=============================================+%n");

            ArrayList<Dish> dishes = new ArrayList<>(order.getDishes().values());
            for (int i = 0; i < dishes.size(); i++) {
                Dish dish = dishes.get(i);
                System.out.format(leftAlignFormat, i, dish.getCount(), dish.getFood().getDescription(),
                        (dish.getReady() ? "X " : "- "));
                System.out.format("+---------------------------------------------+%n");
            }
            System.out.println();

            if (order.checkPrepared()) {
                System.out.println("Order prepared");
                return;
            }

            System.out.print("Insert prepared dish's number or press [e] to exit: ");

            try {
                int dishId;
                do {
                    String nextLine = scan.nextLine();
                    if (nextLine.compareTo("e") == 0)
                        return;

                    dishId = Integer.parseInt(nextLine);
                } while (dishId < 0 || dishId >= dishes.size());

                dishes.get(dishId).setReady(true);
            } catch (NumberFormatException e) {
                System.out.println("Character not allowed");
            }
        } while (!order.checkPrepared());

        System.out.println();
        System.out.println("Order ready!");
    }

    // Clear output console
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Bootloader
    public static void main(String[] args) {
        try {
            new Main().promptHome();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            scan.close();
        }
    }
}