package it.unicam.cs.ids.lg;

import java.util.HashMap;

public class Order
{
    // Dishes in order
    HashMap<Integer, Dish> dishes;

    public Order()
    {
        this.dishes = new HashMap<Integer, Dish>();
    }

    public Order(Order order)
    {
        this.dishes = new HashMap<Integer, Dish>(order.dishes);
    }

    // Add dish to order
    // if it's already then count + num
    public void addDish(int id, Dish dish, int num)
    {
        if (dishes.containsKey(id))
        {
            dishes.get(id).setCount(num + dish.getCount());
        }
        else
        {
            dishes.put(id, dish);
        }
    }

    // Remove dish from order,
    // if count <= num then delete
    // else count -num
    public void removeDish(int id, int num)
    {
        if (!dishes.containsKey(id))
        {
            throw new IllegalArgumentException("Piatto inesistente");
        }

        if (num <= 0)
        {
            throw new IllegalArgumentException("La quantità non può essere minore o uguale a 0");
        }

        Dish dish = dishes.get(id);

        if (dish.getCount() <= num)
        {
            this.dishes.remove(id);
        }
        else
        {
            dishes.get(id).setCount(dish.getCount() - num);
        }
    }

    // Return list order's dishes
    public HashMap<Integer, Dish> getDishes()
    {
        return this.dishes;
    }

    // Check if dish is in order
    public boolean hasDish(int id)
    {
        if (this.dishes.containsKey(id))
            return true;
        else
            return false;
    }

    // Check order's status in kitchen
    public boolean checkPrepared()
    {
        for (Dish d : dishes.values())
        {
            if (!d.getReady())
            {
                return false;
            }
        }
        return true;
    }
}