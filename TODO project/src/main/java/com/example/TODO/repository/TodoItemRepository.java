package com.example.TODO.repository;

import com.example.TODO.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {


    //List<TodoItem> find
    TodoItem findById(long id);

    void deleteById(long id);

    Optional<TodoItem> findByDependentTo(long id);






}
