package com.brianmorais.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private final TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validar(TodoEntity todo) {
        if (existeTodoComDescricao(todo.getDescricao())) {
            throw new IllegalArgumentException("Já existe TODO com esta descrição!");
        }
    }

    private boolean existeTodoComDescricao(String descricao) {
        return todoRepository.existsByDescricao(descricao);
    }
}
