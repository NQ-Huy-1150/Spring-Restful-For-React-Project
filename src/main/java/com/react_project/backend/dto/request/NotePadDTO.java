package com.react_project.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class NotePadDTO {
    private Integer id;
    private String title;
    @NotEmpty
    private String content;
    private Integer catalogId;

    public NotePadDTO() {
    }

    public NotePadDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

}
