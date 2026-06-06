package com.react_project.backend.dto.response;

import java.util.Date;
import java.util.List;

public class TodoListResponse {
    private int id;
    private String title;
    private List<TodoResponse> todos;
    private Date createdAt;
    private Date updatedAt;
    private Integer catalogId;

    public TodoListResponse(int id, String title, List<TodoResponse> todos, Date createdAt, Date updatedAt,
            Integer catalogId) {
        this.id = id;
        this.title = title;
        this.todos = todos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.catalogId = catalogId;
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

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

}
