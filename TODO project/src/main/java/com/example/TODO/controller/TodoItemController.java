package com.example.TODO.controller;

import com.example.TODO.model.ToDoList;
import com.example.TODO.model.TodoItem;
import com.example.TODO.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoItemController {

    @Autowired
    private TodoItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<String> createTodoItem(TodoItem item){
        itemService.createTodoItem(item);
        return ResponseEntity.ok("created item"+item.getId());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<TodoItem> getItem(@PathVariable long id){
        return ResponseEntity.ok().body(itemService.getTodoItem(id));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable long id){
        itemService.deleteTodoFromList(id);
        return ResponseEntity.ok("deleted todo item from");
    }

    @PutMapping("/item/{item}")
    public ResponseEntity<String> markComplete(@PathVariable long item){
        itemService.markComplete(item);
        return ResponseEntity.ok("Kontrol edildi.");
    }
}
