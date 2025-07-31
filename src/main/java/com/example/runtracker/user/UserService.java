package com.example.runtracker.user;

import com.example.runtracker.user.dto.CreateUserRequestDto;
import com.example.runtracker.user.dto.UpdateUserRequestDto;
import com.example.runtracker.user.dto.UserResponseDto;
import com.example.runtracker.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::from)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto request) {
        User mappedUser = userMapper.toEntity(request);
        User savedUser = userRepository.save(mappedUser);

        return userMapper.toResponseDto(savedUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(request.firstName());
                    user.setLastName(request.lastName());
                    user.setEmail(request.email());
                    userRepository.save(user);
                    return userMapper.toResponseDto(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

}
