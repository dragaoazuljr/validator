package com.dmoraes.validator.controller;

import com.dmoraes.validator.dto.PasswordValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PasswordValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnTrueForValidPassword() throws Exception {
        PasswordValidationRequest request = new PasswordValidationRequest("AbTp9!fok");

        mockMvc.perform(post("/api/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    void shouldReturnFalseForInvalidPassword() throws Exception {
        PasswordValidationRequest request = new PasswordValidationRequest("invalid");

        mockMvc.perform(post("/api/v1/password/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }
}