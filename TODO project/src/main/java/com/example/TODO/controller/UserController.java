package com.example.TODO.controller;

import com.example.TODO.model.ToDoList;
import com.example.TODO.model.User;
import com.example.TODO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }


    @PostMapping("/register")
    public ResponseEntity<String >  createUser(@RequestBody User user){
          this.userService.createUser(user);
          return ResponseEntity.ok("created user");
    }

    @DeleteMapping("/secure/users/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("deleted user");
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<String>  createTodoByUser(@PathVariable long userId, @RequestBody ToDoList doList){
        this.userService.createTodoByUser(userId,doList);
        return ResponseEntity.ok("added todo to user" + userId);
    }
}
