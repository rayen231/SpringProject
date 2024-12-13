package com.Recipes.entity;


import javax.persistence.*;

@Entity
@Table(name = "Collections")

public class Collection {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int UserID;
    private int RecipeID;

    public Collection(int id, int UserID, int RecipeID) {
        super();
        this.UserID = 0;
        this.RecipeID = 0;
    }
    public Collection() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id;  }
    public int getUserID() { return UserID; }
    public void setUserID(int UserID) { this.UserID = UserID; }
    public int getRecipeID() { return RecipeID; }
    public void setRecipeID(int RecipeID) { this.RecipeID = RecipeID; }
}
