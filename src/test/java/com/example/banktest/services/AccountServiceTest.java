package com.example.banktest.services;

import com.example.banktest.BanktestApplication;
import com.example.banktest.exceptions.UnauthorizedActionException;
import com.example.banktest.exceptions.UnreachableRessourceException;
import com.example.banktest.models.Account;
import com.example.banktest.models.User;
import com.example.banktest.repos.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BanktestApplication.class)
public class AccountServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    private User alice;

    @Before
    public void init() {
        User bob = new User(2L, "Bob");

        alice = new User(1L, "Alice");
        account = new Account(3L, alice, 0);

        userService.updateUser(alice);
        userService.updateUser(bob);

        accountRepository.deleteAll();
        accountRepository.save(new Account(4L, alice, 100));
        accountRepository.save(new Account(6L, bob, 100));
        accountRepository.save(new Account(5L, alice, 100));
    }

    @Test
    public void assertThatAccountIsGet() {
        accountRepository.save(account);
        var maybeAccount = accountService.getAccount(account.getId()).orElse(null);

        assertThat(maybeAccount).isNotNull();
        assertThat(maybeAccount.getOwner().getId()).isEqualTo(alice.getId());
        assertThat(maybeAccount.getBalance()).isEqualTo(account.getBalance());
    }

    @Test
    public void assertThatAccountByOwnerIsGet() {
        var accounts = accountService.getAccountsByOwner(alice);

        assertThat(accounts.size()).isEqualTo(2);
        accounts.forEach(a -> assertThat(a.getOwner().getId()).isEqualTo(alice.getId()));
    }

    @Test
    public void assertThatWithdrawIsDone() throws UnreachableRessourceException, UnauthorizedActionException {
        var initialBalance = account.getBalance();
        accountRepository.save(account);

        accountService.withdraw(account, 100);
        accountRepository.findById(account.getId()).ifPresentOrElse(a -> assertThat(a.getBalance()).isEqualTo(initialBalance - 100),
                () -> assertThat(false).isTrue()); // TODO use something better for that.
    }

    @Test
    public void assertThatWithdrawFails() throws UnauthorizedActionException, UnreachableRessourceException {
        accountRepository.save(account);
        try {
            accountService.withdraw(account, -100);
            assertThat(true).isFalse();
        } catch (UnauthorizedActionException e) {
            assertThat(true).isTrue();
        }
        var fakeAccount = new Account(-1L ,null, 0);
        try {
            accountService.withdraw(fakeAccount, 100);
            assertThat(true).isFalse();
        } catch (UnreachableRessourceException e) {
            assertThat(true).isTrue();
        }
    }

    @Test
    public void assertThatDepositIsDone() throws UnreachableRessourceException, UnauthorizedActionException {
        var initialBalance = account.getBalance();
        accountRepository.save(account);

        accountService.deposit(account, 100);
        accountRepository.findById(account.getId()).ifPresentOrElse(a -> assertThat(a.getBalance()).isEqualTo(initialBalance + 100),
                () -> assertThat(false).isTrue()); // TODO use something better for that.
    }

    @Test
    public void assertThatDepositFails() throws UnauthorizedActionException, UnreachableRessourceException {
        accountRepository.save(account);
        try {
            accountService.deposit(account, -100);
            assertThat(true).isFalse();
        } catch (UnauthorizedActionException e) {
            assertThat(true).isTrue();
        }
        var fakeAccount = new Account(-1L ,null, 0);
        try {
            accountService.deposit(fakeAccount, 100);
            assertThat(true).isFalse();
        } catch (UnreachableRessourceException e) {
            assertThat(true).isTrue();
        }
    }
}
