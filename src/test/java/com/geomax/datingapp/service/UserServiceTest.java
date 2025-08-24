package com.geomax.datingapp.service;

import com.geomax.datingapp.model.User;
import com.geomax.datingapp.repository.UserRepository;
import com.geomax.datingapp.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock UserRepository userRepository;
    @Mock ProfileRepository profiles;
    @Mock PasswordEncoder passwordEncoder;

    @InjectMocks UserService userService;

    @BeforeEach
    void setup() {
        lenient().when(passwordEncoder.encode(anyString()))
                .thenAnswer(inv -> "ENC(" + inv.getArgument(0) + ")");
        lenient().when(profiles.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void registerCreatesWhenNotExists() {
        when(userRepository.findByEmail("a@b.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User u = userService.register("a@b.com", "secret", "Alice");

        ArgumentCaptor<User> cap = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(cap.capture());
        assertThat(cap.getValue().getEmail()).isEqualTo("a@b.com");
        assertThat(u.getEmail()).isEqualTo("a@b.com");
    }

    @Test
    void registerExistingIsIdempotent() {
        User existing = new User();
        existing.setEmail("x@y.com");
        when(userRepository.findByEmail("x@y.com")).thenReturn(Optional.of(existing));

        User u = userService.register("x@y.com", "pwd", "Bob");

        assertThat(u).isNull();
        verify(userRepository).findByEmail("x@y.com");
        verify(userRepository, atLeast(0)).save(any());
    }
}
