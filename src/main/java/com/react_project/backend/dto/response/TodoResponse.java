package com.react_project.backend.dto.response;

public class TodoResponse {
    private int id;
    private String content;
    private boolean checked;

    public TodoResponse(int id, String content, boolean checked) {
        this.id = id;
        this.content = content;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
