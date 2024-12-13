package com.Recipes.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.Recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Recipes.entity.Recipe;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository bRepo;

	public void save(Recipe b) {
		bRepo.save(b);
	}

	public List<Recipe> getAllRecipes(){
		return bRepo.findAll();
	}

	public Recipe getRecipeById(int id) {
		return bRepo.findById(id) .orElseThrow(() -> new NoSuchElementException("Recipe with ID " + id + " not found"));
	}

	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
	public List<Recipe> getLastRecipes() {return bRepo.findLastThreeRecipes();}

	public List<Recipe> getrecipesbyids(List<Integer> ids ) { return bRepo.findRecipesByIds(ids);}
}
