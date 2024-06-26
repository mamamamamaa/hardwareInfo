package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.TokenEntity;
import com.hardwareInfo.hardwareInfo.entities.UserEntity;
import com.hardwareInfo.hardwareInfo.entities.enums.Role;
import com.hardwareInfo.hardwareInfo.entities.enums.TokenType;
import com.hardwareInfo.hardwareInfo.exceptions.DuplicateEmailException;
import com.hardwareInfo.hardwareInfo.exceptions.DuplicateUsernameException;
import com.hardwareInfo.hardwareInfo.models.AuthenticationRequest;
import com.hardwareInfo.hardwareInfo.models.AuthenticationResponse;
import com.hardwareInfo.hardwareInfo.models.RegisterRequest;
import com.hardwareInfo.hardwareInfo.models.UserDTO;
import com.hardwareInfo.hardwareInfo.repositories.TokenRepository;
import com.hardwareInfo.hardwareInfo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmailException("User with this email already exists");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("User with this username already exists");
        }

        var user = UserEntity.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);

        var userDTO = UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .avatarUrl(user.getAvatarUrl())
                .build();

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDTO)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var userDTO = UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .avatarUrl(user.getAvatarUrl())
                .build();
        var jwtToken = jwtService.generateToken(user);

        checkAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDTO)
                .build();
    }

    private void saveUserToken(UserEntity savedUser, String jwtToken) {
        var token = TokenEntity.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void checkAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> token.setExpired(true));
        tokenRepository.saveAll(validUserTokens);
    }
}
