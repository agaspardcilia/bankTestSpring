package com.example.banktest.services;

import com.example.banktest.exceptions.UnauthorizedActionException;
import com.example.banktest.exceptions.UnreachableRessourceException;
import com.example.banktest.models.Account;
import com.example.banktest.models.User;
import com.example.banktest.repos.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    private UserService userService;

    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Set<Account> getAccountsByOwner(User owner) {
        return accountRepository.findByOwner(owner);
    }

    public void withdraw(Account account, double amount) throws UnauthorizedActionException, UnreachableRessourceException {
        if (amount <= 0)
            throw new UnauthorizedActionException("You can't withdraw less than 1.");

        changeBalance(account, -amount);
    }

    public void deposit(Account account, double amount) throws UnauthorizedActionException, UnreachableRessourceException {
        if (amount <= 0)
            throw new UnauthorizedActionException("You can't deposit less than 1.");

        changeBalance(account, amount);
    }

    private void changeBalance(Account account, double amount) throws UnreachableRessourceException {
        var repoAccount = accountRepository
                .findById(account.getId())
                .orElseThrow(() -> new UnreachableRessourceException("Can't find requested account."));
        repoAccount.setBalance(account.getBalance() + amount);
        accountRepository.save(repoAccount);
    }
}
