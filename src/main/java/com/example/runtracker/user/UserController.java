package com.example.runtracker.user;

import com.example.runtracker.user.dto.UserCreateRequestDto;
import com.example.runtracker.user.dto.UserResponseDto;
import com.example.runtracker.user.dto.UserUpdateRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable @NotNull @Positive(message = "ID has to be positive") Long id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto user) {
        UserResponseDto responseDto = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable @NotNull @Positive(message = "ID has to be positive") Long id,
            @Valid @RequestBody UserUpdateRequestDto user) {

        UserResponseDto responseDto = userService.updateUser(id, user);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable @NotNull @Positive(message = "ID has to be positive") Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
