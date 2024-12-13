package com.Recipes.entity;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String image;

    public User(int id, String name,String image, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.image = image;
    }
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


}
