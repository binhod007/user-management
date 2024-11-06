package com.example.user_management.Controller;
import com.example.user_management.Model.User;
import com.example.user_management.Service.impl.UserServiceImpl; // The service layer class that UserController interacts with.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;// A Spring class used to control the HTTP response, including status codes and body content.
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController// This annotation indicates that this class is a REST controller, meaning it will handle HTTP requests and return JSON-formatted responses.
@RequestMapping("/api/users")
public class UserController {
  private final UserServiceImpl userService;// Declares a final field of type UserService, which will handle business logic and database interactions.
  @Autowired
  public UserController(UserServiceImpl userService){
      this.userService = userService;
  }

  @GetMapping
    public List<User> getAllUsers(){
      return userService.getAllUsers();
  }

  @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
      try {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
      } catch (IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
      }
    }


  @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
    try {
      return ResponseEntity.status(201).body(userService.createUser(user));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }


  @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDatails){
      User updatedUser = userService.updateUser(id, userDatails);
      return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
     try {
       String message = userService.deleteUser(id);
       return ResponseEntity.ok(message);
     } catch (IllegalArgumentException e){
       return ResponseEntity.status(400).body(e.getMessage());
     }
  }
}
