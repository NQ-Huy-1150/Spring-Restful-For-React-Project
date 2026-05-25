package com.react_project.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.domain.TodoList;
import com.react_project.backend.domain.dto.TodoDTO;
import com.react_project.backend.domain.dto.TodoListDTO;
import com.react_project.backend.service.TodoListService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth/v1")
public class TodoListPageController {
    private final TodoListService todoListService;

    public TodoListPageController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/create-todolist")
    public ResponseEntity<TodoListDTO> createTodolist(@Validated @RequestBody TodoListDTO todosDTO) {
        TodoList savedTodoList = this.todoListService.handleSaveTodoList(todosDTO);
        if (savedTodoList != null) {
            TodoListDTO response = new TodoListDTO();
            response.setTitle(savedTodoList.getTitle());
            response.setCreatedAt(savedTodoList.getCreatedAt());
            response.setUpdatedAt(savedTodoList.getUpdatedAt());
            response.setTodos(savedTodoList.getTodos().stream().map(todo -> {
                TodoDTO dto = new TodoDTO();
                dto.setContent(todo.getContent());
                dto.setChecked(todo.getChecked());
                return dto;
            }).toList());
            return ResponseEntity.ok().body(response);
        } else
            return ResponseEntity.badRequest().body(todosDTO);
    }

}
