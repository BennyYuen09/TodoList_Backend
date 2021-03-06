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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Sql(statements = "alter table todo_item alter column id restart with 1")
    @BeforeEach
    void Setup() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_item_list_when_get_todo_list_given_a_todo_repository() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("Hi", false);
        TodoItem todoItem2 = new TodoItem("Hi2", true);

        todoRepository.save(todoItem1);
        todoRepository.save(todoItem2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/todos"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value(todoItem1.getText()))
                .andExpect(jsonPath("$[0].finished").value(todoItem1.getFinished()))

                .andExpect(jsonPath("$[1].text").value(todoItem2.getText()))
                .andExpect(jsonPath("$[1].finished").value(todoItem2.getFinished()))

                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void should_return_todo_item_when_add_todo_item_given_a_todo_item() throws Exception {
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

    @Test
    void should_return_deleted_todo_item_when_delete_todo_item_given_id() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("Hi", false);
        TodoItem todoItem2 = new TodoItem("Hi2", true);

        todoRepository.save(todoItem1);
        todoRepository.save(todoItem2);

        Integer id = todoItem1.getId();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/todos/" + id));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void should_return_updated_todo_item_when_update_todo_item_given_update_info() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("Hi", false);
        TodoItem todoItem2 = new TodoItem("Hi2", true);

        todoItem1 = todoRepository.save(todoItem1);
        todoRepository.save(todoItem2);

        String updateInfo = "{\n" +
                "   \"text\": \"Thoughtworks\",\n" +
                "   \"finished\": true\n" +
                "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(put("/todos/" + todoItem1.getId())
                .contentType(MediaType.APPLICATION_JSON).content(updateInfo));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Thoughtworks"))
                .andExpect(jsonPath("$.finished").value(true));
    }
}
