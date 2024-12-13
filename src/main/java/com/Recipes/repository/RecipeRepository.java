package com.Recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Recipes.entity.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer>  {
    @Query("SELECT r FROM Recipe r WHERE r.id IN (SELECT MAX(id) FROM Recipe) OR r.id = (SELECT MAX(id)-1 FROM Recipe) OR r.id = (SELECT MAX(id)-2 FROM Recipe) ORDER BY r.id DESC")
    List<Recipe> findLastThreeRecipes();
    // Query to fetch recipes by a list of IDs
    @Query("SELECT r FROM Recipe r WHERE r.id IN :ids")
    List<Recipe> findRecipesByIds(List<Integer> ids);





}
