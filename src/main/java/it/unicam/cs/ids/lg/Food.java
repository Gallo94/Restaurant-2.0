package it.unicam.cs.ids.lg;

//La classe rappresenta la pietanza che compone il piatto
public class Food
{
    // Dish's ID
    private int id;
    // Dish's name
    private String description;

    public Food()
    {
        this.id = 0;
        this.description = "";
    }

    public Food(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

     // Return dish's ID
     public int getId()
     {
         return this.id;
     }
 
     // Set dish's ID
     public void setId(int id)
     {
         this.id = id;
     }

     // Return dish's name
     public String getDescription()
     {
         return this.description;
     }
 
     // Set dish's name
     public void setDescription(String description)
     {
         this.description = description;
     }
}