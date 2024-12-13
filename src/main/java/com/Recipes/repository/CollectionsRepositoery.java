package com.Recipes.repository;

import com.Recipes.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionsRepositoery  extends JpaRepository<Collection, Integer> {

    // Find all RecipeIDs for a given UserID
    @Query("SELECT c.RecipeID FROM Collection c WHERE c.UserID = ?1")
    List<Integer> findRecipeIDsByUserID(int userID);

    // Find all UserIDs for a given RecipeID
    @Query("SELECT c.UserID FROM Collection c WHERE c.RecipeID = ?1")
    List<Integer> findUserIDsByRecipeID(int recipeID);


}
