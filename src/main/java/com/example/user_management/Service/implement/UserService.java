package com.example.user_management.Service.implement;
import com.example.user_management.Model.User;// Imports the User model, which is the data type this service will manage.
import com.example.user_management.Repository.UserRepository;//  Imports UserRepository, which provides methods to interact with the database.
import com.example.user_management.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;// Imports @Autowired, an annotation used for dependency injection.
import org.springframework.stereotype.Service;

// Imports utility classes for handling lists and optional values.
import java.util.List;
import java.util.Optional;

// This annotation indicates that UserService is a service component,
// typically used to encapsulate business logic and interact with repositories.
@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException ("User with id " + id + " not found"));
    }

    @Override
    public User createUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User deleted successfully";
        } else {
            throw new IllegalArgumentException("User with id" + id +" not found");
        }
    }
}