package br.com.deborahabreu.todo_list.service;

import br.com.deborahabreu.todo_list.entity.Todo;
import br.com.deborahabreu.todo_list.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> list(){
        return todoRepository.findAll();
    }

    public Todo getById(Long id){
        Optional<Todo> opt = todoRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Tarefa com id " + id + " não encontrada");
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public Optional<Todo> update(Todo todoRequest, Long id){
        Optional<Todo> opt = todoRepository.findById(id);
        if (opt.isPresent()) {
            if (Objects.equals(todoRequest.getId(), id)) {
                todoRepository.save(todoRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do professor com id " + id);
    }

    public void delete(Long id){
        todoRepository.deleteById(id);
    }
}
