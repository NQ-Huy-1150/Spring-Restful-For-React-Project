package com.react_project.backend.dto.request;

import java.sql.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TodolistUpdateDTO {
    private int id;
    private String title;
    @NotNull
    @Valid
    @NotEmpty
    private List<TodoUpdateDTO> todos;
    private Date createdAt;
    private Date updatedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TodoUpdateDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoUpdateDTO> todos) {
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
