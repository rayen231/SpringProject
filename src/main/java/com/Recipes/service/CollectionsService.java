package com.Recipes.service;

import com.Recipes.entity.Collection;
import com.Recipes.repository.CollectionsRepositoery;
import com.Recipes.repository.CollectionsRepositoery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CollectionsService {

    @Autowired
    private CollectionsRepositoery collectionsRepository;

    public List<Integer> getRecipeIDsByUserID(int userID) {
        return collectionsRepository.findRecipeIDsByUserID(userID);
    }

    public List<Integer> getUserIDsByRecipeID(int recipeID) {
        return collectionsRepository.findUserIDsByRecipeID(recipeID);
    }
    public boolean addRecipeToUser(int userID, int recipeID) {
        try {
            // Create a new Collections entity
            Collection collections = new Collection();
            collections.setUserID(userID);
            collections.setRecipeID(recipeID);

            // Save it using the repository's save method
            collectionsRepository.save(collections);

            return true; // Indicate success
        } catch (Exception e) {
            // Handle exceptions and return false for failure
            e.printStackTrace();
            return false;
        }
    }
}
