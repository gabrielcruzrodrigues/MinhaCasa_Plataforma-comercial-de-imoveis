package com.gabriel.minhacasa.security.service;

import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.exceptions.AuthenticationErrorException;
import com.gabriel.minhacasa.exceptions.UserNotFoundException;
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
            User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
            return new AuthenticatedResponseDTO(user.getId(), token, user.getRole().toString());
        } catch (Exception ex) {
            throw new AuthenticationErrorException(ex.getMessage());
        }
    }
}
