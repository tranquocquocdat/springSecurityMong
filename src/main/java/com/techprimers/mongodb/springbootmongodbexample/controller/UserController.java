package com.techprimers.mongodb.springbootmongodbexample.controller;

import com.techprimers.mongodb.springbootmongodbexample.document.User;
import com.techprimers.mongodb.springbootmongodbexample.dto.UserLogin;
import com.techprimers.mongodb.springbootmongodbexample.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return usersService.getUsers();
    }

    @GetMapping("/{userId}")
   public User getUserById(@PathVariable("userId") String userId){
        return usersService.getUserById(userId);
   }

   @PostMapping("/register")
   public User registerUser(@RequestBody User user){
        return usersService.save(user);
   }

   @DeleteMapping("/delete/{userEmail}")
   public void deleteUserByEmail(@PathVariable("userEmail") String userEmail){
        usersService.deleteUserByEmail(userEmail);
   }

   @PutMapping("/update/{UserEmail}")
   public ResponseEntity<User> updateUserByEmail(@PathVariable("UserEmail") String userEmail, @Valid @RequestBody User userDetail){
        return usersService.updateUserByEmail(userEmail,userDetail);
   }
}
