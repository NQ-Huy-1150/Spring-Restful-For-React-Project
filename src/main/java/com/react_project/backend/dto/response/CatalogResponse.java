package com.react_project.backend.dto.response;

public class CatalogResponse {
    private Integer id;
    private String title;

    public CatalogResponse() {

    }

    public CatalogResponse(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
