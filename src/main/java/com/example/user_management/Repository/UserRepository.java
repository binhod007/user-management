package com.example.user_management.Repository;
import com.example.user_management.Model.User;

// Imports the JpaRepository interface from Spring Data JPA, which provides several powerful
// methods for interacting with the database, such as save, findById, and delete
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
