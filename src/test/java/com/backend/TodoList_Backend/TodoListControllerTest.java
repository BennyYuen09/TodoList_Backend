package com.backend.TodoList_Backend;

import com.backend.TodoList_Backend.entity.TodoItem;
import com.backend.TodoList_Backend.mapper.TodoMapper;
import com.backend.TodoList_Backend.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = "alter table todo_item alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class TodoListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoListRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @BeforeEach
    void Setup() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_item_list_when_get_todo_list_given_a_todo_repository () throws Exception{
        //given
        TodoItem todoItem1 = new TodoItem("Hi", false);
        TodoItem todoItem2 = new TodoItem("Hi2", true);

        todoRepository.save(todoItem1);
        todoRepository.save(todoItem2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/todos"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value(todoItem1.getText()))
                .andExpect(jsonPath("$[0].finished").value(todoItem1.isFinished()))

                .andExpect(jsonPath("$[1].text").value(todoItem2.getText()))
                .andExpect(jsonPath("$[1].finished").value(todoItem2.isFinished()))

                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void should_return_todo_item_when_add_todo_item_given_a_todo_item () throws Exception{
        //given
        String item = "{\n" +
                "   \"text\": \"Thoughtworks\",\n" +
                "   \"finished\": true\n" +
                "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON).content(item));

        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Thoughtworks"))
                .andExpect(jsonPath("$.finished").value(true));
    }
}
