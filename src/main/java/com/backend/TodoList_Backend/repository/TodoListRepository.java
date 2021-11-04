package com.backend.TodoList_Backend.repository;

import com.backend.TodoList_Backend.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoItem, Integer> {

}
