package com.example.demo.repository;

import com.example.demo.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByFirstNameTest() {
        User user = new User("Kojo");
        userRepository.save(user);
        assertTrue(userRepository.findUserByFirstName("Kojo").isPresent());
    }
}