package com.massaricompany.eventsapp.events.service.user;

import com.massaricompany.eventsapp.events.exception.ResourceNotFoundException;
import com.massaricompany.eventsapp.events.exception.UserNotFoundException;
import com.massaricompany.eventsapp.events.model.User;
import com.massaricompany.eventsapp.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    // Add methods for CRUD operations and any additional business logic

    // Create or Update a User
    public User save(User user) {
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            return null;
        }
    }

    // Retrieve a User by ID
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Retrieve a User by Username or Email containing
    public List<User> findByUsernameContainingOrEmailContaining(String username, String email) {
        List<User> users = new ArrayList<>();
        if (username != null) {
            users.addAll(userRepository.findByUsernameContainingIgnoreCase(username));
        }
        if (email != null) {
            users.addAll(userRepository.findByEmailContainingIgnoreCase(email));
        }
        return users;
    }

    // Retrieve all Users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Delete a User by ID
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Update User
    public User update(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }

        User currentUser = findById(id);

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            currentUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().isEmpty()) {
            currentUser.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isEmpty()) {
            currentUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getRole() != null && !updatedUser.getRole().isEmpty()) {
            currentUser.setRole(updatedUser.getRole());
        }
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            currentUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            currentUser.setPassword(updatedUser.getPassword());
        }

        return userRepository.save(currentUser);
    }

    // Get total amount of Users
    public long getTotalUsers() {
        return userRepository.count();
    }



}