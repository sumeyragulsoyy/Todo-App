package com.example.TODO.dao;

import com.example.TODO.model.ToDoList;
import com.example.TODO.model.TodoItem;
import com.example.TODO.service.TodolistService;

import java.util.List;


public interface ToDoListDao {

    //create todo list
    void CreateTodoList(ToDoList toDoList);

    //delete todolist
    void DeleteToDoList(long listId);

    ToDoList getListById(long listId);

    // list todolists by userid
    List<ToDoList> getTodosbyUserId(long userId);


    //get /user/1/todo/5   todoId
    ToDoList getTodobyIdOfUserId(long userID,long todoId);


    //create todo by userId
    void createTodoByUserId(long userId);

    void addTodoItemByListId(long listId, TodoItem item);




}
