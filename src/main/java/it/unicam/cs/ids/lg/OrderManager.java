package it.unicam.cs.ids.lg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OrderManager
{
    // List orderes
    private ArrayList<Order> orders;
    // Restaurant's menu
    private ArrayList<Food> menu;

    public OrderManager(ArrayList<Food> menu)
    {
        if (menu == null)
            throw new NullPointerException("Menu cannot be null");
        if (menu.isEmpty())
            throw new IllegalArgumentException("Menu cannot be empty");

        this.orders = new ArrayList<>();
        this.menu = menu;
    }

    // Return list orders
    public ArrayList<Order> getOrders()
    {
        return this.orders;
    }

    // Show home's interface
    public void promptHome()
    {
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        do
        {
            System.out.println();
            System.out.println("+========================+");
            System.out.println("|          HOME          |");
            System.out.println("|--------+---------------|");
            System.out.println("|  [1].  |  MENU         |");
            System.out.println("|  [2].  |  NEW ORDER    |");
            System.out.println("|  [3].  |  ORDERS       |");
            System.out.println("|  [4].  |  EXIT         |");
            System.out.println("+========+===============+");
            System.out.println();
            System.out.print("Insert a number from 1 to 4: ");

            try
            {
                do 
                {
                    choice = Integer.parseInt(scan.nextLine());
                    switch (choice)
                    {
                        case 1:
                            printMenu();
                            break;
                        case 2:
                            promptNewOrderMenu();
                            break;
                        case 3:
                            promptOrdersMenu();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Option wrongs");
                    }
                }
                while (choice < 1 || choice > 4);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Character not allowed");
            }
        }
        while (choice != 4);
    }

    // Print restaurant's menu
    public void printMenu()
    {
        System.out.println("");
        System.out.println("+================================+");
        System.out.println("|               MENU             |");
        System.out.println("|================================|");
    
        for (Food food : menu)
        {
            System.out.println("| " + food.getId()+ " | " + food.getDescription() + " |");
        }
        System.out.println("+================================+");
    }

    // Show options to add/delete/confirm order
    public void promptNewOrderMenu()
    {
        Order order = new Order();
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        do
        {
            clearScreen();
            printMenu();
            System.out.println();
            System.out.println("+========================+");
            System.out.println("|         ORDER          |");
            System.out.println("|--------+---------------|");
            System.out.println("|  [1]   |  ADD DISH     |");
            System.out.println("|  [2]   |  DEL DISH     |");
            System.out.println("|  [3]   |  CONFIRM      |");
            System.out.println("|  [4]   |  BACK         |");
            System.out.println("+========+===============+");
            System.out.println();
            printOrder(order);
            System.out.print("Insert a number from 1 to 4: ");
            
            try
            {
                do 
                {
                    choice = Integer.parseInt(scan.nextLine());
                    switch (choice)
                    {
                        case 1:
                            promptAddDish(order);
                            break;
                            case 2:
                            promptRemoveDish(order);
                            break;
                            case 3:
                            if (order.getDishes().isEmpty())
                            {
                                System.out.println("Order has no dishes");
                                choice = 0;
                            }
                            else
                            {
                                this.orders.add(order);
                            }
                            break;
                        case 4:
                            break;
                        default:
                        System.out.println("Option wrongs");
                    }
                }
                while (choice < 1 || choice > 4);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Character not allowed");
            }
        }
        while (choice != 4 && choice != 3);
    }

    // Add dish to order
    public void promptAddDish(Order order)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("");
        System.out.print("ID : ");
        int choiceId = Integer.parseInt(scan.nextLine());
        System.out.print("QTY: ");
        int choiceQta = Integer.parseInt(scan.nextLine());
        
        if ((choiceId >= 0 && choiceId <= menu.size()-1) && choiceQta > 0)
        {
            if (order.hasDish(choiceId))
            {
                HashMap<Integer, Dish> map = order.getDishes();
                Dish dish = map.get(choiceId);
                dish.setCount(dish.getCount() + choiceQta);
            }
            else
            {
                order.addDish(choiceId, new Dish(choiceQta, menu.get(choiceId)), choiceQta);
            }
        }
        else
        {
            System.out.println("Dish wrongs");
        }
    }

    // Remove dish from order
    public void promptRemoveDish(Order order)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("");
        System.out.print("ID : ");
        int choiceId = Integer.parseInt(scan.nextLine());
        System.out.print("QTY: ");
        int choiceQta = Integer.parseInt(scan.nextLine());

        if ((choiceId >= 0 && choiceId <= menu.size()-1) && choiceQta > 0)
        {
            if (order.hasDish(choiceId))
            {
                HashMap<Integer, Dish> map = order.getDishes();
                Dish dish = map.get(choiceId);
                
                if (dish.getCount() <= choiceQta)
                {
                    order.removeDish(choiceId, choiceQta);
                }
                else
                {
                    dish.setCount(dish.getCount() - choiceQta);
                }
            }
        }
    }

    // Print dish in order
    public void printOrder(Order order)
    {
        for (Dish dish : order.getDishes().values())
        {
            System.out.println("-> " + " [QTY:" + dish.getCount() + "]" + " | " + "[ID:" + dish.getFood().getId() + "] " + dish.getFood().getDescription());
        }
        System.out.println();
    }

    // Show all orders confirmed
    public void promptOrdersMenu()
    {
        if (orders.size() == 0)
        {
            System.out.println();
            System.out.println("No orders");
            return;
        }
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("+-------------+");

        for (int i = 0; i < orders.size(); i++)
        {
            System.out.println(" [ID:" + i + "] " + "ORDER");
        }
        System.out.println("+-------------+");
        System.out.println();

        System.out.print("Insert order number or press [e] to exit: ");

        try
        {
            int orderId;
            do 
            {
                String nextLine = scan.nextLine();
                if (nextLine.compareTo("e") == 0)
                    return;

                orderId = Integer.parseInt(nextLine);
            }
            while (orderId < 0 || orderId >= orders.size());

            promptOrderDetails(this.orders.get(orderId));
        }
        catch(NumberFormatException e)
        {
            System.out.println("Character not allowed");
        }
    }

    // Show order's details
    public void promptOrderDetails(Order order)
    {
        Scanner scan = new Scanner(System.in);
        
        do
        {
            System.out.println();
            System.out.println("+===============================================+");
            System.out.println("|                     DETAILS                   |");
            System.out.println("|-----------------------------------------------|");
            
            ArrayList<Dish> dishes = new ArrayList<>(order.getDishes().values());
            for (int i = 0; i < dishes.size(); i++)
            {
                Dish dish = dishes.get(i);
                System.out.println("|" + " [N:" + i + "] " + dish.getFood().getDescription() + " [QTY:" + dish.getCount() + "] " + (dish.getReady() ? "  X " : "  - ")  + " |");
            }
            System.out.println("+===============================================+");
            System.out.println();
            
            if (order.checkPrepared())
            {
                System.out.println("Order prepared");
                return;
            }

            System.out.println("Insert prepared dish's number or press [e] to exit: ");
            
            try
            {
                int dishId;
                do 
                {
                    String nextLine = scan.nextLine();
                    if (nextLine.compareTo("e") == 0)
                        return;

                    dishId = Integer.parseInt(nextLine);
                }
                while (dishId < 0 || dishId >= dishes.size());
                
                dishes.get(dishId).setReady(true);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Character not allowed");
            }
        }
        while (!order.checkPrepared());

        System.out.println();
        System.out.println("Order ready!");
    }
    

    // Clear output console
    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}