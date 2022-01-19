package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User("Ben"));
        users.add(new User("Dzifa"));

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();
        Assertions.assertEquals(2, allUsers.size());
    }

    @Nested
    @DisplayName("Should add new user")
    class addNewUser {
        @Captor
        private ArgumentCaptor<User> userArgumentCaptor;

        @Test
        @DisplayName("given a valid user")
        void addNewUserTest() {
            User user = new User("dzifa");
            userService.addNewUser(user);
//            ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
            verify(userRepository).save(userArgumentCaptor.capture());

            User capturedUser = userArgumentCaptor.getValue();
            Assertions.assertEquals(capturedUser, user);
            Assertions.assertEquals("dzifa", capturedUser.getFirstName());
        }

        @Test
        @DisplayName("given user already exists, throw exception")
        void addNewUserThrowsException() {
            User user = new User(2L, "ama");
            when(userRepository.findById(2L)).thenReturn(Optional.of(user));
            Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addNewUser(user));
        }
    }


}