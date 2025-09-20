package br.com.deborahabreu.todo_list.controller;

import br.com.deborahabreu.todo_list.entity.Todo;
import br.com.deborahabreu.todo_list.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> list(){
        return todoService.list();
    }

    @GetMapping("/{id}")
    public Todo list(@PathVariable Long id){
        return todoService.getById(id);
    }

    @PostMapping
    public List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @PutMapping("/{id}")
    public Optional<Todo> update(@RequestBody Todo todo, @PathVariable Long id){
        return todoService.update(todo, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        todoService.delete(id);
    }
}
