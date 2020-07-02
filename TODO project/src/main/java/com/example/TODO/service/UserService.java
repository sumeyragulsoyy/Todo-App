package com.example.TODO.service;

import com.example.TODO.dao.UserDao;
import com.example.TODO.exception.ResourceNotFoundException;
import com.example.TODO.model.ToDoList;
import com.example.TODO.model.User;
import com.example.TODO.repository.ToDoListRepository;
import com.example.TODO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Override
    public void createUser(User user)
    {
          userRepository.save(user);
    }

    //@Override
    //public boolean userExist(User user) {
    //    Optional<User> userDb=this.userRepository.findById(user.getId());
    //    return  userDb.isPresent();
    //}

    @Override
    public List<User> getAllUsers() {
        return  this.userRepository.findAll();
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> userDb=this.userRepository.findById(userId);
        if(userDb.isPresent()){
            return userDb.get();
        }else {
            throw  new ResourceNotFoundException("Record not found with ID: "+userId);
        }
        //userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() :null
    }

    @Override
    public void deleteUser(long userId) {
        Optional<User> userDb=this.userRepository.findById(userId);
        if(userDb.isPresent()){
            this.userRepository.delete(userDb.get());
        }else{
            throw  new ResourceNotFoundException("Record not found with ID: "+userId);
        }
    }


    //create todo list by user
    @Override
    public void createTodoByUser(long userId, ToDoList doList) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.findById(userId).get().getToDoLists().add(doList);
            userRepository.save(userRepository.findById(userId).get());
        }
    }
}
