package com.stock.portfolio.controllers;

import com.stock.portfolio.models.User;
import com.stock.portfolio.security.AuthenticationService;
import com.stock.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/stock/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    // User Permissions

    @GetMapping("/myself")
    public User getMyself() throws Exception {
        return authenticationService.getCurrentUser();
    }

    @GetMapping("myself/details")
    public UserDetails getMyDetails() throws Exception {
        return authenticationService.getCurrentUserDetails();
    }

    // ADMIN Permissions

    @GetMapping("/find/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        if (id == null) {
            throw new ValidationException("Provided empty data");
        }
        return userService.findById(id);
    }

    @GetMapping("/find/username/{username}")
    public User getUserByUsername(@PathVariable String username) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Provided empty data");
        }
        return userService.findByUsername(username);
    }

    @GetMapping("/find/email/{email}")
    public User getUserByEmail(@PathVariable String email) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new ValidationException("Provided empty data");
        }
        return userService.findByEmail(email);
    }

    @GetMapping("/findAll/active")
    public List<User> getAllActiveUsers() throws Exception { return userService.findAllActive(); }

    @GetMapping("/findAll/inactive")
    public List<User> getAllInactiveUsers() throws Exception { return userService.findAllInactive(); }

    @GetMapping("/findAll/admin")
    public List<User> getAllAdmins() throws Exception { return userService.findAllAdmins(); }

    @GetMapping("/findAll/user")
    public List<User> getAllUsers() throws Exception { return userService.findAllUsers(); }

    @GetMapping("/findAll/ids")
    public List<Long> getAllUserIds() throws Exception { return userService.findAllUserIds(); }

    @GetMapping("/findAll/emails")
    public List<String> getAllUserEmails() throws Exception { return userService.findAllUserEmails(); }

    @GetMapping("/findAll")
    public List<User> getAll() throws Exception { return userService.findAll(); }

    @GetMapping("/findAllById")
    public List<User> getAllUsersById(List<Long> ids) throws Exception {
        if (ids == null) {
            throw new ValidationException("Provided empty data");
        }
        return userService.findAllById(ids);
    }

    @PutMapping("/save")
    public void saveUser(@RequestBody User user) throws Exception {
        if (user != null) {
            userService.save(user);
        }
    }

    @PutMapping("/saveAll")
    public void saveAllUsers(@RequestBody List<User> users) throws Exception {
        if (users != null) {
            userService.saveAll(users);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        if (id != null) {
            userService.deleteById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUsers() throws Exception {
        userService.deleteAll();
    }

    @PostMapping("/activate/{id}")
    public void activateUserById(Long id) {
        if (id != null) {
            User user = userService.findById(id);
            if (user != null) {
                userService.activateUser(user);
            }
        }
    }

    @PostMapping("/deactivate/{id}")
    public void deactivateUserById(Long id) {
        if (id != null) {
            User user = userService.findById(id);
            if (user != null) {
                userService.deactivateUser(user);
            }
        }
    }
}
