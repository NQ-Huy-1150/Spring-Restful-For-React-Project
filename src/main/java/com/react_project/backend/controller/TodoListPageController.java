package com.react_project.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.domain.Todo;
import com.react_project.backend.domain.TodoList;
import com.react_project.backend.dto.request.TodoDTO;
import com.react_project.backend.dto.request.TodoListDTO;
import com.react_project.backend.dto.request.TodolistUpdateDTO;
import com.react_project.backend.dto.response.MessageResponse;
import com.react_project.backend.dto.response.TodoListResponse;
import com.react_project.backend.dto.response.TodoResponse;
import com.react_project.backend.service.TodoListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class TodoListPageController {
    private final TodoListService todoListService;

    public TodoListPageController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/create-todolist")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> createTodolist(@Validated @RequestBody TodoListDTO todosDTO) {
        TodoList savedTodoList = this.todoListService.handleSaveTodoList(todosDTO);
        if (savedTodoList != null) {
            TodoListDTO response = new TodoListDTO();
            response.setId(savedTodoList.getId());
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
            return ResponseEntity.badRequest().body(new MessageResponse("Cant create Todolist !"));
    }

    @GetMapping("/get-todolists")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getAllTodoList() {
        List<TodoList> lists = this.todoListService.fetchAllTodoList();
        List<TodoListResponse> todoListResponses = new ArrayList<TodoListResponse>();
        if (lists != null) {
            for (TodoList list : lists) {
                List<TodoResponse> todoResponses = new ArrayList<TodoResponse>();
                for (Todo todo : list.getTodos()) {
                    TodoResponse obj = new TodoResponse(todo.getId(), todo.getContent(), todo.getChecked());
                    todoResponses.add(obj);
                }
                TodoListResponse response = new TodoListResponse(list.getId(), list.getTitle(), todoResponses,
                        list.getCreatedAt(), list.getUpdatedAt());
                todoListResponses.add(response);
            }
        }
        return ResponseEntity.ok(todoListResponses);
    }

    @GetMapping("/todolist/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getTodoListById(@PathVariable int id) {
        Optional<TodoList> optional = this.todoListService.getTodoListById(id);
        if (optional.isPresent()) {
            TodoList todoList = optional.get();
            List<TodoResponse> todoResponses = new ArrayList<TodoResponse>();
            for (Todo todo : todoList.getTodos()) {
                TodoResponse obj = new TodoResponse(todo.getId(), todo.getContent(), todo.getChecked());
                todoResponses.add(obj);
            }
            TodoListResponse response = new TodoListResponse(todoList.getId(), todoList.getTitle(), todoResponses,
                    todoList.getCreatedAt(), todoList.getUpdatedAt());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
    }

    @PutMapping("/modify-todolist")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> modifyTodolist(@RequestBody TodolistUpdateDTO todoListDTO) {
        if (this.todoListService.isExistedById(todoListDTO.getId())) {
            this.todoListService.handleModifyTodoList(todoListDTO);
            return ResponseEntity.ok().body(new MessageResponse("Modify successfully !"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found !"));
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getDelete(@PathVariable int id) {
        if (this.todoListService.getDelete(id)) {
            return ResponseEntity.ok(new MessageResponse("Deleted successfully !"));
        }
        return ResponseEntity.badRequest().body("Id not found !");
    }
}
