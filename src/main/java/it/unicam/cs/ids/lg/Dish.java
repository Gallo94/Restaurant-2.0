package it.unicam.cs.ids.lg;

public class Dish
{
    // Amount dish
    private int count;
    // Food
    private Food food;
    // Status order
    private boolean ready;

    public Dish()
    {
        this.count = 0;
        this.food = null;
    }

    public Dish(int count, Food food)
    {
        this.count = count;
        this.food = food;
        this.ready = false;
    }

    // Return amount's dish 
    public int getCount()
    {
        return this.count;
    }

    // Set amount's dish
    public void setCount(int count)
    {
        this.count = count;
    }

    // Return ordered dish
    public Food getFood()
    {
        return this.food;
    }

    // Set dish in order
    public void setFood(Food food)
    {
        this.food = food;
    }

    // Return dish's status(ready)
    public boolean getReady()
    {
        return this.ready;
    }
    
    // Set dish's status(ready)
    public void setReady(boolean ready)
    {
        this.ready = ready;
    }
}