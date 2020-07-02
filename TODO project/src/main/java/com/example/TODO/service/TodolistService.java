package com.example.TODO.service;

import com.example.TODO.dao.ToDoListDao;
import com.example.TODO.exception.ResourceNotFoundException;
import com.example.TODO.model.Status;
import com.example.TODO.model.ToDoList;
import com.example.TODO.model.TodoItem;
import com.example.TODO.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
@Transactional
public class TodolistService implements ToDoListDao {

    @Autowired
    private ToDoListRepository toDoListRepository;


    @Override
    public void CreateTodoList(ToDoList toDoList) {
        toDoListRepository.save(toDoList);

    }

    //delete todo list
    @Override
    public void DeleteToDoList(long listId) {
        Optional<ToDoList> listDb=this.toDoListRepository.findById(listId);
        if (listDb.isPresent()){
            this.toDoListRepository.delete(listDb.get());
        }else{
            throw  new ResourceNotFoundException("Record not found with ID: "+listId);
        }

    }

    @Override
    public ToDoList getListById(long listId) {
        Optional<ToDoList> listDb=this.toDoListRepository.findById(listId);
        if(listDb.isPresent()){
            return listDb.get();
        }else {
            throw  new ResourceNotFoundException("Record not found with ID: "+listId);
        }

    }

    // filter items of list to status complete or not, expired, name

    public  List<TodoItem> filterItemsToStatusByListId(long listId,String status){
        ToDoList list=getListById(listId);
        Predicate<TodoItem> byStatus;
        if (status.equals("COMPLETED")){
            byStatus=item -> item.getStatus() == Status.COMPLETED;
         }else{
             byStatus =item -> item.getStatus() == Status.PROGRESS;
         }
         List<TodoItem>  filtered=list.getItems().stream().filter(byStatus).collect(Collectors.toList());
         return  filtered;
    }


    //expired or not

    public List<TodoItem> filteredToExpiration(long listId,String expiredOrNot){ // E,NE(NOT EXPIRED)
        Date today = Calendar.getInstance().getTime();
        ToDoList list=getListById(listId);
        Predicate<TodoItem>  byExpiration;
        if(expiredOrNot.equals("E")){
            byExpiration= item -> item.getDeadline().compareTo(today)<0;
        }else  {
            byExpiration= item -> (item.getDeadline().compareTo(today)>0 || item.getDeadline().compareTo(today)==0);
        }
        List<TodoItem>  filtered=list.getItems().stream().filter(byExpiration).collect(Collectors.toList());
        return  filtered;
    }

    //name
    public List<TodoItem> filteredByName(long listId,String name){
        ToDoList list=getListById(listId);
        Predicate<TodoItem>  byName =item -> item.getName().equals(name);
        List<TodoItem>  filtered=list.getItems().stream().filter(byName).collect(Collectors.toList());
        return  filtered;
    }


    //Order to-do items on a to-do list by create date, deadline, name, or status.

    public List<TodoItem> sortByCriteria(long listId,String sortBy){
        ToDoList list=getListById(listId);
        List<TodoItem> sorted=list.getItems();
        switch (sortBy){
            case "createdDate":
                Collections.sort(sorted,Comparator.comparing(item -> item.getCreatedDate()));
                break;
            case "deadline":
                Collections.sort(sorted,Comparator.comparing(item -> item.getDeadline()));
                break;
            case "name":
                Collections.sort(sorted,Comparator.comparing(item -> item.getName()));
                break;
            case "status":
                Collections.sort(sorted,Comparator.comparing(item -> item.getStatus()));

        }

        return sorted;
    }




    @Override
    public void createTodoByUserId(long userId) {


    }

    //add todo item to existing todo list
    @Override
    public void addTodoItemByListId(long listId, TodoItem item) {
        if(toDoListRepository.findById(listId).isPresent()){
            toDoListRepository.findById(listId).get().getItems().add(item);
            toDoListRepository.save(toDoListRepository.findById(listId).get());
        }
    }


    //list of todolist of USER
    @Override
    public List<ToDoList> getTodosbyUserId(long userId) {
        return toDoListRepository.findToDoListsById(userId);
    }

    @Override
    public ToDoList getTodobyIdOfUserId(long userID, long todoId) {
        return null;
    }

    //public List<ToDoList> getListsByUser(long id){
     //   return toDoListRepository.findToDoListsByustofid(id);
    //}


}
