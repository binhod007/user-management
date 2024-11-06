package com.example.user_management.Controller;
import com.example.user_management.Model.User;
import com.example.user_management.Service.UserServiceInterface;
import com.example.user_management.Service.implement.UserService; // The service layer class that UserController interacts with.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;// A Spring class used to control the HTTP response, including status codes and body content.
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController// This annotation indicates that this class is a REST controller, meaning it will handle HTTP requests and return JSON-formatted responses.
@RequestMapping("/api/users")
public class UserController {
  private final UserServiceInterface userService;// Declares a final field of type UserService, which will handle business logic and database interactions.
  @Autowired
  public UserController(UserServiceInterface userService){
      this.userService = userService;
  }

  @GetMapping
    public List<User> getAllUsers(){
      return userService.getAllUsers();
  }

//  @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable Long id){
//      try {
//        User user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//      } catch (IllegalArgumentException e){
//        return ResponseEntity.status(400).body(e.getMessage());
//      }
//    }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id); // Will throw an exception if not found
  }


//  @PostMapping
//    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
//    try {
//      User createUser = userService.createUser(user);
//      return ResponseEntity.status(201).body(createUser);
//    } catch (IllegalArgumentException e) {
//      return ResponseEntity.status(400).body(e.getMessage());
//    }
//  }

  @PostMapping
  public  User createUser(@Valid @RequestBody User user){
    return userService.createUser(user);
  }


//  @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDatails){
//      User updatedUser = userService.updateUser(id, userDatails);
//      return ResponseEntity.ok(updatedUser);
//  }

  @PutMapping("/{id}")
  public  User updateUser(@PathVariable Long id, @RequestBody User userDetails){
    return userService.updateUser(id, userDetails);
  }

//  @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id){
//     try {
//       String message = userService.deleteUser(id);
//       return ResponseEntity.ok(message);
//     } catch (IllegalArgumentException e){
//       return ResponseEntity.status(400).body(e.getMessage());
//     }
//  }

  @DeleteMapping("/{id}")
  public  String deleteUser(@PathVariable Long id){
    return userService.deleteUser(id);
  }
}
