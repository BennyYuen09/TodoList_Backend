package com.backend.TodoList_Backend.dto;

public class TodoRequest {
    private String text;
    private boolean finished;

    public TodoRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
