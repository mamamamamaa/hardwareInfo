package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.entities.UserEntity;
import com.hardwareInfo.hardwareInfo.models.AuthenticationRequest;
import com.hardwareInfo.hardwareInfo.models.AuthenticationResponse;
import com.hardwareInfo.hardwareInfo.models.RegisterRequest;
import com.hardwareInfo.hardwareInfo.models.UserDTO;
import com.hardwareInfo.hardwareInfo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/auth")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserEntity user) {
        UserDTO userDTO = convertToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    private UserDTO convertToUserDTO(UserEntity user) {
        return UserDTO.builder()
                .username(user.getEmail())
                .email(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .build();
    }
}
