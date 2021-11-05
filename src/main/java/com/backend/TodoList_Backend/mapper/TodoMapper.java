package com.backend.TodoList_Backend.mapper;

import com.backend.TodoList_Backend.dto.TodoRequest;
import com.backend.TodoList_Backend.dto.TodoResponse;
import com.backend.TodoList_Backend.entity.TodoItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoResponse toResponse(TodoItem todoItem) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todoItem, todoResponse);
        return todoResponse;
    }

    public TodoItem toEntity(TodoRequest todoRequest) {
        TodoItem todoItem = new TodoItem();
        BeanUtils.copyProperties(todoRequest, todoItem);
        return todoItem;
    }
}
