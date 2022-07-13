package com.techprimers.mongodb.springbootmongodbexample.controller;

import com.techprimers.mongodb.springbootmongodbexample.document.User;
import com.techprimers.mongodb.springbootmongodbexample.security.ApplicationUserRoles;
import com.techprimers.mongodb.springbootmongodbexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/users")

public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
//    hasRole('ROLE_') hasAnyRole('ROLE') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('student_read')")
    public List<User> getAll() {
        System.out.println(ApplicationUserRoles.ADMIN.getGrantedAuthorities());
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
   public User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
   }

   @PostMapping("/register")
   public User registerUser(@RequestBody User user, HttpServletRequest request){
        request.getAttribute("cookie");
        return userService.save(user);
   }

   @DeleteMapping("/delete/{userEmail}")
   public void deleteUserByEmail(@PathVariable("userEmail") String userEmail){
        userService.deleteUserByEmail(userEmail);
   }

   @PutMapping("/update/{UserEmail}")
   public ResponseEntity<User> updateUserByEmail(@PathVariable("UserEmail") String userEmail, @Valid @RequestBody User userDetail){
        return userService.updateUserByEmail(userEmail,userDetail);
   }
}
