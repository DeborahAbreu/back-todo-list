package br.com.deborahabreu.todo_list.service;

import br.com.deborahabreu.todo_list.entity.Todo;
import br.com.deborahabreu.todo_list.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    private Todo todo;

    private List<Todo> todoList;

    @BeforeEach
    void setup(){
        todo = new Todo(10L, "teste", false);
        todoList = new ArrayList<>();
        todoList.add(todo);
    }

    @Test
    void whenCreateTodoSuccessufly_ShouldReturnTodoList(){
        when(todoRepository.save(todo)).thenReturn(todo);
        when(todoRepository.findAll()).thenReturn(todoList);

        assertEquals(todoList, todoService.create(todo));

        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    void whenCreateTodoFail_ShouldThrowsException(){
        doThrow(new RuntimeException("falha ao salvar")).when(todoRepository).save(todo);
        assertThrows(RuntimeException.class, () -> todoService.create(todo));
        verify(todoRepository, times(1)).save(todo);
    }


}
