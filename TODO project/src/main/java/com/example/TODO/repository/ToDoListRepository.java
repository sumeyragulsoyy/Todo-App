package com.example.TODO.repository;

import com.example.TODO.model.ToDoList;
import com.example.TODO.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList,Long> {

    List<ToDoList> findToDoListsById(Long id);

}
