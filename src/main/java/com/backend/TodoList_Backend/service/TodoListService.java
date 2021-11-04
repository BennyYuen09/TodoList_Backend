package com.backend.TodoList_Backend.service;

import com.backend.TodoList_Backend.dto.TodoResponse;
import com.backend.TodoList_Backend.entity.TodoItem;
import com.backend.TodoList_Backend.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoItem> getTodoList() {
        return todoListRepository.findAll();
    }

    public TodoItem addTodoItem(TodoItem todoItem) {
        return null;
    }
}
