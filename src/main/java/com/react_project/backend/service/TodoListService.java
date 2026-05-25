package com.react_project.backend.service;

import org.springframework.stereotype.Service;

import com.react_project.backend.domain.TodoList;
import com.react_project.backend.domain.User;
import com.react_project.backend.domain.dto.TodoListDTO;
import com.react_project.backend.repository.TodoListRepository;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final TodoService todoService;

    public TodoListService(TodoListRepository todoListRepository, TodoService todoService) {
        this.todoListRepository = todoListRepository;
        this.todoService = todoService;
    }

    public TodoList handleConvertDTO(TodoListDTO todoListDTO) {
        if (todoListDTO == null) {
            throw new IllegalArgumentException("TodoListDTO must not be null");
        }
        TodoList todoList = new TodoList();
        User user = new User();
        user.setId(1);
        todoList.setTitle(todoListDTO.getTitle());
        todoList.setCreatedAt(todoListDTO.getCreatedAt());
        todoList.setUpdatedAt(todoListDTO.getUpdatedAt());
        todoList.setUser(user);
        todoList.setTodos(todoListDTO.getTodos().stream()
                .map(dto -> this.todoService.HandleConvertTodoDTO(dto, todoList)).toList());
        return todoList;
    }

    public TodoList handleSaveTodoList(TodoListDTO todoListDTO) {
        TodoList todos = handleConvertDTO(todoListDTO);
        if (todos != null) {
            todos = this.todoListRepository.save(todos);
        }
        return todos;
    }

}
