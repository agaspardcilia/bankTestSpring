package com.example.banktest.repos;

import com.example.banktest.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByName(String name);
}
