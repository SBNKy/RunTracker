package com.example.runtracker.user.dto;

import com.example.runtracker.user.User;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
//    public static UserResponseDto from(User user) {
//        return new UserResponseDto(
//                user.getId(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail()
//        );
//    }
}


