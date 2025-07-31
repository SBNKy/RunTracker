package com.example.runtracker.user;

import com.example.runtracker.user.dto.UserCreateRequestDto;
import com.example.runtracker.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequestDto request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}
