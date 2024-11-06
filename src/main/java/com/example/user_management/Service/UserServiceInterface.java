package com.example.user_management.Service;
import com.example.user_management.Model.User;
import java.util.List;


public interface UserServiceInterface {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    String deleteUser(Long id);
}
