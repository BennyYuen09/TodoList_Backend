package com.backend.TodoList_Backend;

import com.backend.TodoList_Backend.entity.TodoItem;
import com.backend.TodoList_Backend.repository.TodoListRepository;
import com.backend.TodoList_Backend.service.TodoListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TodoListServiceTest {
    @Mock
    private TodoListRepository todoListRepository;

    @InjectMocks
    private TodoListService todoListService;

    @Test
    void should_return_all_todo_items_when_get_todo_list_given_three_todo_items (){
        //given
        List<TodoItem> expected = Arrays.asList(
                new TodoItem("Axel", false),
                new TodoItem("UB_soft", true),
                new TodoItem("EA_trash", false)
        );

        when(todoListRepository.findAll()).thenReturn(expected);

        //when
        List<TodoItem> actual = todoListService.getTodoList();

        //then
        assertEquals(expected, actual);
    }
}
