package com.dmoraes.validator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.dmoraes.validator.service.impl.PasswordValidationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidationServiceTest {

    private PasswordValidationServiceImpl validationService;

    @BeforeEach
    void setUp() {
        validationService = new PasswordValidationServiceImpl();
    }

    @Test
    void shouldReturnTrueForValidPassword() {
        assertTrue(validationService.isValid("AbTp9!fok"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",                // empty
        "aa",              // too short
        "ab",              // too short
        "AAAbbbCc",        // no special char and digit
        "AbTp9!foo",       // repeated char
        "AbTp9!foA",       // repeated char
        "AbTp9 fok",       // contains space
        "abcd123!@#",      // no uppercase
        "ABCD123!@#",      // no lowercase
        "ABCDabcd!@#",     // no digit
        "ABCDabcd123"      // no special char
    })
    void shouldReturnFalseForInvalidPasswords(String password) {
        assertFalse(validationService.isValid(password));
    }

    @Test
    void shouldReturnFalseForNullPassword() {
        assertFalse(validationService.isValid(null));
    }
}
