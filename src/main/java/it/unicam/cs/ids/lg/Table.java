package it.unicam.cs.ids.lg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table
{
    // Table's ID
    private int id;
    // Table's Number seats
    private int numSeats;
    private Order order;
    
    public Table()
    {
        this.id = 0;
        this.numSeats = 0;
        this.order = null;
    }

    public Table(final int id, final int numSeats)
    {
        this.id = id;
        this.numSeats = numSeats;
        this.order = null;
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

    // Set table as free
    public void free()
    {
        this.order = null;
    }

    // Check if table is free
    public boolean isFree()
    {
        return this.order == null;
    }
}
