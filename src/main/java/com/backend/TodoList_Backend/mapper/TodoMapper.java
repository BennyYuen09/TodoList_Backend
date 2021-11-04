package com.backend.TodoList_Backend.mapper;

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
}
