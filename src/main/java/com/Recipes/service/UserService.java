package com.Recipes.service;

import com.Recipes.entity.User;
import com.Recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    // Create or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Update user details
    public User updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDetails.getName());
            user.setPassword(userDetails.getPassword());
            // Update other fields as needed
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    // Delete user by ID
    public boolean deleteUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Validate user credentials
    public boolean validateUser(String name, String password) {
        User user = userRepository.findByNameAndPassword(name, password);
        return user != null;
    }
    public int findUserByName(String name) {return  userRepository.findByName(name).getId();}

    public void setUserIdInSession(int userid) {
        httpSession.setAttribute("userIdInSession", userid);
    }
}
