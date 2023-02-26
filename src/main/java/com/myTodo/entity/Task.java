package com.myTodo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "content", nullable = false)
    private String content ;

    @Column(name = "completed", nullable = false)
    private boolean completed =Boolean.FALSE;



    public Task() {
    }

    public Task(long id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
