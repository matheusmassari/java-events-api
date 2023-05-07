package com.massaricompany.eventsapp.events.controller;

import com.massaricompany.eventsapp.events.dto.UserResponse;
import com.massaricompany.eventsapp.events.exception.EmailAlreadyExistsException;
import com.massaricompany.eventsapp.events.model.User;
import com.massaricompany.eventsapp.events.service.user.UserService;
import com.massaricompany.eventsapp.events.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Authenticate User
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }



    // Create or Update a User

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);
        if (savedUser == null) {
            throw new EmailAlreadyExistsException("Email address is already in use");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setUsername(savedUser.getUsername());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setRole(savedUser.getRole());


        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // Retrieve a User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Retrieve a User by Username or Email
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> search(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "email", required = false) String email) {
        try {
            List<User> users = userService.findByUsernameContainingOrEmailContaining(username, email);
            List<UserResponse> userResponses = users.stream().map(user -> {
                UserResponse userResponse = new UserResponse();
                userResponse.setId(user.getId());
                userResponse.setUsername(user.getUsername());
                userResponse.setFirstName(user.getFirstName());
                userResponse.setLastName(user.getLastName());
                userResponse.setEmail(user.getEmail());
                userResponse.setRole(user.getRole());
                return userResponse;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(userResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Retrieve all Users
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Update a User by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User updated = userService.update(id, updatedUser);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    // Delete a User by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get total amount of Users
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalUsers() {
        long totalUsers = userService.getTotalUsers();
        return ResponseEntity.ok(totalUsers);
    }

}
