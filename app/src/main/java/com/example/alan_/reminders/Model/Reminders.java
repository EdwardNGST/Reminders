package com.example.alan_.reminders.Model;

public class Reminders {
    private int id;
    private String title;
    private String text;
    private int priority;

    public Reminders(int id, String title, String text, int priority) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.priority = priority;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriority() {
        return priority;
    }
}
