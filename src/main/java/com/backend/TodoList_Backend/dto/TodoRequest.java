package com.backend.TodoList_Backend.dto;

public class TodoRequest {
    private String text;
    private Boolean finished;

    public TodoRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
