package it.unicam.cs.ids.lg;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table
{
    // Table's ID
    private int id;
    // Table's Number seats
    private int numSeats;
    private Order order;
    private ArrayList<Order> previousOrders;
    
    public Table()
    {
        this.id = 0;
        this.numSeats = 0;
        this.order = null;
        this.previousOrders = null;
    }

    public Table(final int id, final int numSeats)
    {
        this.id = id;
        this.numSeats = numSeats;
        this.order = null;
        this.previousOrders = new ArrayList<>();
    }
    
    // Set table's ID
    public void setId(int id)
    {
        if (id <= 0)
            throw new IllegalArgumentException("Table's id cannot be less or equal than 0");

        this.id = id;
    }

    // Return table's ID
    public int getId()
    {
        return id;
    }

    // Set table's number seats
    public void setNumSeats(int numSeat)
    {
        if (numSeat <= 0)
            throw new IllegalArgumentException("Seats cannot be less or equal than 0");

        this.numSeats = numSeat;
    }

    // Return table's number seats
    public int getNumSeats()
    {
        return this.numSeats;
    }

    // Set table's order
    public void setOrder(Order order)
    {
        this.order = order;
    }

    // Return table's order
    public Order getOrder()
    {
        return this.order;
    }

    public void setPreviousOrders(ArrayList<Order> previousOrders)
    {
        this.previousOrders = previousOrders;
    }

    public ArrayList<Order> getPreviousOrders()
    {
        return previousOrders;
    }

    // Set table as free
    public void free()
    {
        // Save to history
        if (previousOrders == null)
            previousOrders = new ArrayList<>();
            
        previousOrders.add(new Order(order));

        // Delete
        order = null;
    }

    // Check if table is free
    public boolean isFree()
    {
        return order == null;
    }
}
