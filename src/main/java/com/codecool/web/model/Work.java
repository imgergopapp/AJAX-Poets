package com.codecool.web.model;

import java.time.LocalDate;

public class Work {
    private int id;
    private LocalDate date;
    private String title;
    private String content;

    public Work(int id,LocalDate date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
