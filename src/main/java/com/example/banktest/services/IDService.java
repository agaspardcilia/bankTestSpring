package com.example.banktest.services;

import org.springframework.stereotype.Service;

@Service
public class IDService {
    private int id;

    public IDService() {
        this.id = 1;
    }

    public int nextID() {
        return id++;
    }
}
