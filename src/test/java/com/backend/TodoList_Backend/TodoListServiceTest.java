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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void should_return_todo_item_when_add_todo_item_given_a_todo_item () {
        //given
        TodoItem expected = new TodoItem("Text", true);

        when(todoListRepository.save(any(TodoItem.class))).thenReturn(expected);

        //when
        TodoItem actual = todoListService.addTodoItem(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_todo_item_when_delete_item_given_a_id (){
        //given
        TodoItem expected = new TodoItem("test", false);
        expected.setId(1);
        when(todoListRepository.findById(any())).thenReturn(Optional.of(expected));
        doNothing().when(todoListRepository).deleteById(expected.getId());

        //when
        TodoItem actual = todoListService.deleteById(expected.getId());

        //then
        verify(todoListRepository, times(1)).deleteById(expected.getId());
        assertEquals(expected.getId(), actual.getId());
    }
}
