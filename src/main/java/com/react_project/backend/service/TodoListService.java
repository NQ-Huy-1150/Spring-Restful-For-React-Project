package com.react_project.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.react_project.backend.entity.Catalog;
import com.react_project.backend.entity.Todo;
import com.react_project.backend.entity.TodoList;
import com.react_project.backend.entity.User;
import com.react_project.backend.dto.request.TodoDTO;
import com.react_project.backend.dto.request.TodoListDTO;
import com.react_project.backend.dto.request.TodoUpdateDTO;
import com.react_project.backend.dto.request.TodolistUpdateDTO;
import com.react_project.backend.repository.TodoListRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final TodoService todoService;
    private final CatalogService catalogService;
    private final CurrentUserService currentUserService;

    public TodoListService(TodoListRepository todoListRepository, TodoService todoService,
            CatalogService catalogService, CurrentUserService currentUserService) {
        this.todoListRepository = todoListRepository;
        this.todoService = todoService;
        this.catalogService = catalogService;
        this.currentUserService = currentUserService;
    }

    public TodoList handleConvertDTO(TodoListDTO todoListDTO) {
        if (todoListDTO == null) {
            throw new IllegalArgumentException("TodoListDTO must not be null");
        }
        TodoList todoList = new TodoList();
        User user = currentUserService.getCurrentUser();
        todoList.setTitle(todoListDTO.getTitle());
        Date time = new Date();
        todoList.setCreatedAt(time);
        todoList.setUpdatedAt(todoListDTO.getUpdatedAt());
        todoList.setUser(user);
        if (todoListDTO.getCatalogId() == null) {
            todoList.setCatalog(null);
        } else {
            Optional<Catalog> optional = this.catalogService.getCatalogById(todoListDTO.getCatalogId());
            Catalog cata = optional.orElseThrow(
                    () -> new RuntimeException("Catalog Id not found !" + todoListDTO.getCatalogId()));
            todoList.setCatalog(cata);
        }
        List<TodoDTO> requestTodos = Objects.requireNonNullElse(todoListDTO.getTodos(), List.of());
        todoList.setTodos(requestTodos.stream()
                .map(dto -> this.todoService.HandleConvertTodoDTO(dto, todoList)).toList());
        return todoList;
    }

    @Transactional
    public TodoList handleSaveTodoList(TodoListDTO todoListDTO) {
        TodoList todos = handleConvertDTO(todoListDTO);
        if (todos != null) {
            todos = this.todoListRepository.save(todos);
        }
        return todos;
    }

    public List<TodoList> fetchAllTodoList() {
        return this.todoListRepository.findAllByUser_Id(currentUserService.getCurrentUserId());
    }

    public Optional<TodoList> getTodoListById(int id) {
        return this.todoListRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId());
    }

    @Transactional
    public void handleModifyTodoList(TodolistUpdateDTO todoListDTO) {
        Optional<TodoList> optional = todoListRepository.findByIdAndUser_Id(
                todoListDTO.getId(),
                currentUserService.getCurrentUserId());
        List<TodoUpdateDTO> incomingTodos = Objects.requireNonNullElse(todoListDTO.getTodos(), List.of());
        if (optional.isPresent()) {
            TodoList currentList = optional.get();
            currentList.setTitle(todoListDTO.getTitle());
            Date time = new Date();
            currentList.setUpdatedAt(time);
            if (todoListDTO.getCatalogId() != null) {
                Optional<Catalog> cataOptional = this.catalogService.getCatalogById(todoListDTO.getCatalogId());
                if (cataOptional.isPresent()) {
                    Catalog cata = cataOptional.get();
                    currentList.setCatalog(cata);
                }
            }
            for (Todo existing : currentList.getTodos()) {
                for (TodoUpdateDTO dto : incomingTodos) {
                    if (dto.getId() != null && dto.getId() == existing.getId()) {
                        existing.setContent(dto.getContent());
                        existing.setChecked(dto.getChecked());
                        break;
                    }
                }
            }
            List<Todo> removedItem = handleGetDeletedTodoIdFromDTO(incomingTodos, currentList);
            List<Todo> addItem = handleGetNewTodoFromDTO(incomingTodos);
            removedItem.forEach(currentList::removeTodo);
            addItem.forEach(currentList::addTodo);
            this.todoListRepository.save(currentList);
        }
    }

    public List<Todo> handleGetDeletedTodoIdFromDTO(List<TodoUpdateDTO> todoDTO, TodoList currentTodoList) {
        Set<Integer> removed = todoDTO.stream().filter(t -> t.getId() != null).map(t -> t.getId())
                .collect(Collectors.toSet());
        List<Todo> removeTodos = currentTodoList.getTodos().stream().filter(t -> !removed.contains(t.getId())).toList();
        for (Todo todo : removeTodos) {
            System.err.println(">>>>>>>>>>>>>>>>>> REMOVED-TODO-ID :" + todo.getId());
        }
        return removeTodos;
    }

    public List<Todo> handleGetNewTodoFromDTO(List<TodoUpdateDTO> todoDTOs) {
        List<TodoUpdateDTO> tds = todoDTOs.stream().filter(t -> t.getId() == null).toList();
        List<Todo> todos = new ArrayList<Todo>();
        for (TodoUpdateDTO todoDTO : tds) {
            Todo td = new Todo(0, todoDTO.getContent(), todoDTO.getChecked());
            todos.add(td);
            System.out.println(">>>>>>>> NEW TODO ID : " + todoDTO.getId());
            System.out.println(">>>>>>>> NEW TODO CONTENT : " + todoDTO.getContent());
            System.out.println(">>>>>>>> NEW TODO CHECKED : " + todoDTO.getChecked());
        }
        return todos;
    }

    @Transactional
    public boolean getDelete(int id) {
        Optional<TodoList> optional = todoListRepository.findByIdAndUser_Id(
                id,
                currentUserService.getCurrentUserId());
        if (optional.isEmpty()) {
            return false;
        }
        todoListRepository.delete(optional.get());
        return true;
    }

    public boolean isExistedById(int id) {
        return this.todoListRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId()).isPresent();
    }
}
