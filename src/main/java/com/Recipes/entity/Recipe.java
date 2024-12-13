package com.Recipes.entity;

import javax.persistence.*;

@Entity
@Table(name = "Recipe")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String image;
	private String link;
	public Recipe(int id, String name, String description, String image, String link) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.link = link;
	}
	public Recipe() {
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLink() {return link;}
	public void setLink(String link) {this.link = link;}
	
}
