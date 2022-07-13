package com.techprimers.mongodb.springbootmongodbexample.service;

import com.techprimers.mongodb.springbootmongodbexample.document.User;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class UsersService {
    UserRepository userRepository;
    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow((()->new IllegalArgumentException("not found user")));
    }

    public void deleteUserByEmail(String userEmail) {
        userRepository.deleteById(userEmail);
    }

    public ResponseEntity<User> updateUserByEmail(String userEmail, @Valid User userDetail) {
        User user=userRepository.findById(userEmail).orElseThrow(()->new SecurityException("user not found on:"+ userEmail));

        user.setEmail(userDetail.getEmail());
        user.setTeamName(userDetail.getTeamName());
        user.setPassword(userDetail.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
