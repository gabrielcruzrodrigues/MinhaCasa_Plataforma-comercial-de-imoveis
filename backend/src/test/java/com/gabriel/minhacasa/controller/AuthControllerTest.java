package com.gabriel.minhacasa.controller;

import com.gabriel.minhacasa.domain.User;
import com.gabriel.minhacasa.repository.UserRepository;
import com.gabriel.minhacasa.security.DTO.AuthenticatedResponseDTO;
import com.gabriel.minhacasa.security.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    @DisplayName("must to do the login by user and return a access token with success")
    void login_whenToCall_mustToDoLoginAndReturnAAccessToken() throws Exception {
        AuthenticatedResponseDTO authenticate = new AuthenticatedResponseDTO(1L, "Token", "USER");
        User user = new User();
        when(this.authenticationService.loginUser(anyString(), anyString())).thenReturn(authenticate);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        String jsonRequest = "{\"email\": \"test@example.com\", \"password\": \"12345678a!\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("Token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("USER"));
    }

    @Test
    @DisplayName("must return a status 400 when the jsonRequest is not complete.")
    void login_whenJsonRequestIsNotComplete_mustReturnAStatus400() throws Exception {
        String jsonRequest = "{\"email\": \"test@example.com\", \"password\": }";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}