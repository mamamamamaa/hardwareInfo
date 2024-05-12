package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.models.AuthenticationRequest;
import com.hardwareInfo.hardwareInfo.models.AuthenticationResponse;
import com.hardwareInfo.hardwareInfo.models.RegisterRequest;
import com.hardwareInfo.hardwareInfo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/sign_in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
