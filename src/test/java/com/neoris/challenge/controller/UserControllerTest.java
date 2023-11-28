package com.neoris.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.challenge.dto.UserDTO;
import com.neoris.challenge.service.UserService;
import com.neoris.challenge.validatior.EmailValidator;
import com.neoris.challenge.validatior.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private PasswordValidator passwordValidator;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsersTest() throws Exception {
        UserDTO user = new UserDTO(); // Setup user DTO
        given(userService.getAllUsers()).willReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserByIdTest() throws Exception {
        UUID userId = UUID.randomUUID();
        UserDTO user = new UserDTO(); // Setup user DTO
        given(userService.getUserById(userId)).willReturn(user);

        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deleteUserTest() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isNoContent());
    }
}
