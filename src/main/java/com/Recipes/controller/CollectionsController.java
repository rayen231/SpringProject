package com.Recipes.controller;

import com.Recipes.entity.Recipe;
import com.Recipes.service.CollectionsService;
import com.Recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private RecipeService service ;

    @GetMapping("/recipes/{userID}")
    public ResponseEntity<List<Integer>> getRecipeIDsByUserID(@PathVariable int userID) {
        List<Integer> recipeIDs = collectionsService.getRecipeIDsByUserID(userID);
        return ResponseEntity.ok(recipeIDs);
    }

    @GetMapping("/users/{recipeID}")
    public ResponseEntity<List<Integer>> getUserIDsByRecipeID(@PathVariable int recipeID) {
        List<Integer> userIDs = collectionsService.getUserIDsByRecipeID(recipeID);
        return ResponseEntity.ok(userIDs);
    }
    @GetMapping("/recipes/add")
    public ModelAndView addRecipe(@RequestParam String userID, @RequestParam int recipeID, HttpSession session, Model model) {
        int userId = (int) session.getAttribute("userId");
        boolean isAdded = collectionsService.addRecipeToUser(userId, recipeID);

        if (isAdded) {
            List<Integer> recipeIDs = collectionsService.getRecipeIDsByUserID(userId);
            List<Recipe> recipes =  service.getrecipesbyids(recipeIDs);
            model.addAttribute("userId", userId);
            System.out.println(userId);
            model.addAttribute("recipes", recipes);
            return new ModelAndView("MyRecipes", "recipes", recipes);
        } else {
            return new ModelAndView("home", "empty","empty");
        }
    }


}
