package com.react_project.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class CatalogDTO {
    @NotEmpty
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CatalogDTO() {
    }

    public CatalogDTO(String title) {
        this.title = title;
    }

}
