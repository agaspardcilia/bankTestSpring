package com.example.banktest.services;

import com.example.banktest.models.User;
import com.example.banktest.repos.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void init() {
        user = new User(1L, "Alice");

        userRepository.deleteAll();
        userRepository.save(new User(2L, "b"));
        userRepository.save(new User(2L, "b"));
        userRepository.save(new User(3L, "c"));
        userRepository.save(new User(4L, "d"));
    }

    @Test
    public void assertThatAUserIsAdded() {
        var usersCount = userRepository.count();
        userService.addUser("foo");

        assertThat(userRepository.count()).isEqualTo(usersCount + 1);
    }

    @Test
    public void assertThatAnUserCanBeFoundByID() {
        userRepository.save(user);
        var maybeUser = userService.getUserById(user.getId());

        assertThat(maybeUser.orElseThrow().getId()).isEqualTo(user.getId());
        assertThat(maybeUser.orElseThrow().getName()).isEqualTo(user.getName());
    }

    @Test
    public void assertThatAnUserCanBeFoundByName() {
        userRepository.save(user);
        var maybeUser = userService.getUserByName(user.getName());

        assertThat(maybeUser.orElseThrow().getId()).isEqualTo(user.getId());
        assertThat(maybeUser.orElseThrow().getName()).isEqualTo(user.getName());
    }

    @Test
    public void assertCannotBeFound() {
        assertThat(userService.getUserById(user.getId()).isPresent()).isFalse();
        assertThat(userService.getUserByName(user.getName()).isPresent()).isFalse();
    }

    @Test
    public void assertThatAUserIsGettingUpdated() {
        userRepository.save(user);
        var updatedUser = new User(user.getId(), "Bob");
        userService.updateUser(updatedUser);

        assertThat(userRepository.findById(user.getId()).orElseThrow().getName()).isEqualTo("Bob");
    }

    @Test
    public void assertThatAUserIsNotGettingUpdated() {
        userRepository.save(user);
        var updatedUser = new User("Bob");
        userService.updateUser(updatedUser);

        assertThat(userRepository.findById(user.getId()).orElseThrow().getName()).isNotEqualTo("Bob");
    }
}
