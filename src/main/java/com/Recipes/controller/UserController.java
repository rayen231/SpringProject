package com.Recipes.controller;

import com.Recipes.entity.Recipe;
import com.Recipes.entity.User;
import com.Recipes.service.RecipeService;
import com.Recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService service ;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Validate user by name and password
    @PostMapping("/validate")
    public String validateUser(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String password, Model model, HttpSession session) {
        System.out.println(name);
        System.out.println(password);

        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            // Add an error message to redirectAttributes for display
            model.addAttribute("error", "Name and password must not be empty.");
            return "login";
        }


        boolean isValid = userService.validateUser(name, password);
        if (isValid) {
            userService.setUserIdInSession(userService.findUserByName(name));
            session.setAttribute("userId", userService.findUserByName(name));
            List<Recipe> list = service.getLastRecipes();
            model.addAttribute("recipes", list);
            System.out.println(list);
            return "home";
        } else {
// Add an error message to redirectAttributes for display
            model.addAttribute("error", "Invalid credentials.");
            return "login"; // Redirects back to login page
        }
    }

}
