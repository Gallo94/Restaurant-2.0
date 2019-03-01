package it.unicam.cs.ids.lg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table
{
    private int id;
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
    
    public void setId(int id)
    {
        if (id <= 0)
            throw new IllegalArgumentException("Table's id cannot be less or equal than 0");

        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setNumSeats(int numSeat)
    {
        if (numSeat <= 0)
            throw new IllegalArgumentException("Seats cannot be less or equal than 0");

        this.numSeats = numSeat;
    }

    public int getNumSeats()
    {
        return this.numSeats;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Order getOrder()
    {
        return this.order;
    }

    public void free()
    {
        this.order = null;
    }

    public boolean isFree()
    {
        return this.order == null;
    }
}
