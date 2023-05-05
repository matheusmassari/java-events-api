package com.massaricompany.eventsapp.events.repository;

import com.massaricompany.eventsapp.events.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameContainingIgnoreCase(String username);
    List<User> findByEmailContainingIgnoreCase(String email);

}
