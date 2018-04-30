package com.example.banktest.services;

import org.springframework.stereotype.Service;

@Service
public class BankService {
    private AccountService accountService;

    public BankService(AccountService accountService) {
        this.accountService = accountService;
    }
}
