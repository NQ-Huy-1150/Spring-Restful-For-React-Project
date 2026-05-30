package com.react_project.backend.domain.dto.response;

import java.util.Date;
import java.util.List;

public class TodoListResponse {
    private int id;
    private String title;
    private List<TodoResponse> todos;
    private Date createdAt;
    private Date updatedAt;

    public TodoListResponse(int id, String title, List<TodoResponse> todos, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.todos = todos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TodoResponse> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoResponse> todos) {
        this.todos = todos;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
