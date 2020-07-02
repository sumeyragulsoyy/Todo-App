package com.example.TODO.dao;

import com.example.TODO.model.ToDoList;
import com.example.TODO.model.User;

import java.util.List;

public interface UserDao {

    //insert or add user
    void createUser(User user);

    //get all users
    List<User> getAllUsers();

    //get user
    User getUserById(long userId);

    void deleteUser(long userId);

    void createTodoByUser(long userId, ToDoList doList);


}
