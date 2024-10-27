package com.brianmorais.arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoValidator todoValidator;
    private final MailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValidator todoValidator, MailSender mailSender) {
        this.todoRepository = todoRepository;
        this.todoValidator = todoValidator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity novoTodo) {
        todoValidator.validar(novoTodo);
        return todoRepository.save(novoTodo);
    }

    public TodoEntity atualizarStatus(TodoEntity todo) {
        var atualizado = todoRepository.save(todo);
        String concluido = todo.getConcluido() == Boolean.TRUE ? "Concluído" : "Não concluído";
        mailSender.enviar("Todo " + todo.getDescricao() + " foi atualizado para " + concluido);
        return atualizado;
    }

    public TodoEntity buscarPorId(Integer id) {
        return todoRepository.findById(id).orElse(null);
    }
}
