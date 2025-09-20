package br.com.deborahabreu.todo_list.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Todo {

    private Long id;
    private String descricao;
    private boolean estaFeita;

}