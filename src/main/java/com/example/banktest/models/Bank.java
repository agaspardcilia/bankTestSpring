package com.example.banktest.models;

import java.util.Set;

public class Bank {
    private int id;
    private String name;
    private Set<Account> accounts;
    private Set<User> customers;

    public Bank(int id, String name, Set<Account> accounts, Set<User> customers) {
        this.id = id;
        this.name = name;
        this.accounts = accounts;
        this.customers = customers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<User> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<User> customers) {
        this.customers = customers;
    }
}
