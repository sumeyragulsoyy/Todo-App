package com.example.TODO.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="todolists")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;


    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "list_items_fk",referencedColumnName = "id")
    List<TodoItem> items=new ArrayList<>();


    public ToDoList(){ }

    public ToDoList(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }
}
