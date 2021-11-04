package com.backend.TodoList_Backend.controller;

import com.backend.TodoList_Backend.dto.TodoResponse;
import com.backend.TodoList_Backend.entity.TodoItem;
import com.backend.TodoList_Backend.mapper.TodoMapper;
import com.backend.TodoList_Backend.dto.TodoRequest;
import com.backend.TodoList_Backend.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    //@CrossOrigin(origins = "http://localhost:3000")
    public List<TodoResponse> getTodoList() {
        return todoListService.getTodoList()
                .stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@CrossOrigin(origins = "http://localhost:3000")
    public TodoResponse addTodoItem(@RequestBody TodoRequest todoRequest) {
        TodoItem todoItem = todoListService.addTodoItem(todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todoItem);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        todoListService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodoItem(@PathVariable("id") Integer id, @RequestBody TodoRequest todoRequest) {
        TodoItem todoItem = todoListService.updateTodoItem(id, todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todoItem);
    }
}
