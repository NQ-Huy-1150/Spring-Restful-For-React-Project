package com.react_project.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CatalogUpdateDTO {
    @NotNull
    private int id;
    @NotEmpty
    private String title;

    public CatalogUpdateDTO() {
    }

    public CatalogUpdateDTO(@NotNull int id, @NotEmpty String title) {
        this.id = id;
        this.title = title;
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

}
