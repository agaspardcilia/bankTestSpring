package com.example.banktest.repos;

import com.example.banktest.models.Account;
import com.example.banktest.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
   Set<Account> findByOwner(User owner);
}
