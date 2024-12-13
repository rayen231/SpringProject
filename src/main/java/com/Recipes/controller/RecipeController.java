package com.Recipes.controller;

import com.Recipes.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.Recipes.entity.Recipe;
import com.Recipes.service.RecipeService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService service;
	@Autowired
	private CollectionsService collectionsService;

	// Home page
	@GetMapping("/")
	public String login() {
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model){
		model.asMap().clear();
		session.invalidate();
		return "login";
	}
	@GetMapping("/My_Recipes")
	public ModelAndView myrecipes(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		List<Integer> recipeIDs = collectionsService.getRecipeIDsByUserID(userId);
		List<Recipe> recipes =  service.getrecipesbyids(recipeIDs);
		model.addAttribute("userId", userId);
        System.out.println(userId);
        model.addAttribute("recipes", recipes);
        return new ModelAndView("MyRecipes", "recipes", recipes);
	}
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		int userid= (int) session.getAttribute("userId");
		List<Recipe> list = service.getLastRecipes();
		model.addAttribute("recipes", list);
		System.out.println(list);
		model.addAttribute("userid", userid);
		System.out.println(userid);
		return "home";
	}
	@GetMapping("/login")
	public String relogin()
	{
		return "login";
	}

	// Recipe registration form
	@GetMapping("/recipe_register")
	public String recipeRegister(Model model) {
		model.addAttribute("recipe", new Recipe());
		return "recipeRegister";
	}

	// Display all recipes
	@GetMapping("/available_recipes")
	public ModelAndView getAllRecipes(Model model, HttpSession session) {
		List<Recipe> list = service.getAllRecipes();
		int userid= (int) session.getAttribute("userId");
		model.addAttribute("userid", userid);
		return new ModelAndView("recipeList", "recipes", list);
	}

	// Add a new recipe
	@PostMapping("/save_recipe")
	public String addRecipe(@ModelAttribute Recipe recipe) {
		service.save(recipe);
		return "redirect:/available_recipes";
	}

	// Edit a recipe by ID
	@RequestMapping("/edit_recipe/{id}")
	public String showEditRecipeForm(@PathVariable("id") int id, Model model) {
		Recipe recipe = service.getRecipeById(id);
		model.addAttribute("recipe", recipe);
		return "recipeEdit";
	}

	// Delete a recipe by ID
	@RequestMapping("/delete_recipe/{id}")
	public String deleteRecipe(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_recipes";
	}
}
