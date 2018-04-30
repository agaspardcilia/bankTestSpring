package com.example.banktest.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {
    @Id
    private Long id;

    @ManyToOne
    private User owner;

    private double balance;

    public Account() {
    }

    public Account(Long id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
