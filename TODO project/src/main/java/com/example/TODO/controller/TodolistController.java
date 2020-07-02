package com.example.TODO.controller;


import com.example.TODO.model.ToDoList;
import com.example.TODO.model.TodoItem;
import com.example.TODO.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodolistController {

    @Autowired
    private TodolistService todolistService;


    @GetMapping("/todolist/{id}")
    public ResponseEntity<ToDoList> getTodoById(@PathVariable long id){
        return ResponseEntity.ok().body(todolistService.getListById(id));
    }


    @PostMapping("/todolist")
    public ResponseEntity<String>  createTodo(@RequestBody ToDoList list){
        this.todolistService.CreateTodoList(list);
        return ResponseEntity.ok("created todo");
    }

    @DeleteMapping("/todolist/{id}")
    public ResponseEntity<String>  deleteTodo(@PathVariable long id){
        this.todolistService.DeleteToDoList(id);
        return ResponseEntity.ok("deleted todo");
    }

    @PostMapping("/todolist/{listId}")
    public ResponseEntity<String> addTodoItemByListId(@PathVariable long listId,@RequestBody TodoItem item){
        todolistService.addTodoItemByListId(listId,item);
        return ResponseEntity.ok("created item"+item.getId());
    }


    @GetMapping("/todolist/{listId}/filter/{filter}")
    public  ResponseEntity<List<TodoItem>> filterItemBy(@PathVariable  long listId,@PathVariable String filter){
        List<TodoItem> filtered=null;
        if(filter.equals("COMPLETED") || filter.equals("PROGRESS")){
            filtered =todolistService.filterItemsToStatusByListId(listId,filter);
        }else if (filter.equals("E")|| filter.equals("NE")){
           filtered=todolistService.filteredToExpiration(listId,filter);
        }else { //name filtering
            filtered=todolistService.filteredByName(listId,filter);
        }
        return ResponseEntity.ok().body(filtered);
    }


    @GetMapping("/todolist/{listId}/sort/{sortBy}")
    public ResponseEntity<List<TodoItem>> sortedBy(@PathVariable long listId, @PathVariable String sortBy){
        return ResponseEntity.ok().body(todolistService.sortByCriteria(listId,sortBy));
    }
}
