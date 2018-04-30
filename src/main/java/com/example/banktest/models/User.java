package com.example.banktest.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.id = -1L;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
