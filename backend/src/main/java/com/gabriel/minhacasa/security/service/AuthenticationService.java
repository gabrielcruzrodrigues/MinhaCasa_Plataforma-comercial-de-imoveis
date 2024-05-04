package com.gabriel.minhacasa.security.service;

import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.repository.UserRepository;
import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticatedResponseDTO loginUser(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtTokenService.generateToken(auth);
            Optional<User> user = userRepository.findByEmail(email);
            return new AuthenticatedResponseDTO(user.get().getId(), token);
        } catch (Exception ex) {
            return new AuthenticatedResponseDTO(null, ex.getMessage());
        }
    }
}
