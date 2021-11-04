package com.backend.TodoList_Backend.controller;

import com.backend.TodoList_Backend.dto.TodoResponse;
import com.backend.TodoList_Backend.mapper.TodoMapper;
import com.backend.TodoList_Backend.service.TodoListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoListController {
    private final TodoListService todoListService;
    private final TodoMapper todoMapper;

    public TodoListController(TodoListService todoListService, TodoMapper todoMapper) {
        this.todoListService = todoListService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<TodoResponse> getTodoList (){
        return todoListService.getTodoList().stream().map(todoMapper::toResponse).collect(Collectors.toList());
    }
}
