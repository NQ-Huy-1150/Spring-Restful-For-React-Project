package com.react_project.backend.service;

import org.springframework.stereotype.Service;

import com.react_project.backend.domain.Todo;
import com.react_project.backend.domain.TodoList;
import com.react_project.backend.domain.dto.TodoDTO;
import com.react_project.backend.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo HandleConvertTodoDTO(TodoDTO todoDTO, TodoList todos) {
        Todo todo = new Todo();
        todo.setContent(todoDTO.getContent());
        todo.setChecked(todoDTO.getChecked());
        todo.setTodoList(todos);
        return todo;
    }

}
