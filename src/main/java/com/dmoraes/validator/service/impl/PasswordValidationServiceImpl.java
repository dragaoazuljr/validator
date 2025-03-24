package com.dmoraes.validator.service.impl;

import org.springframework.stereotype.Service;

import com.dmoraes.validator.service.PasswordValidationService;

import java.util.HashSet;
import java.util.Set;

@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {
    private static final String SPECIAL_CHARS = "!@#$%^&*()-+";
    private static final int MIN_LENGTH = 9;

    @Override
    public boolean isValid(String password) {
        if (password == null || password.trim().isEmpty() || password.contains(" ")) {
            return false;
        }

        if (password.length() < MIN_LENGTH) {
            return false;
        }

        if (hasRepeatedCharacters(password)) {
            return false;
        }

        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasSpecial = password.chars().anyMatch(ch -> SPECIAL_CHARS.indexOf(ch) >= 0);

        return hasDigit && hasLower && hasUpper && hasSpecial;
    }

    private boolean hasRepeatedCharacters(String password) {
        Set<Character> chars = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!chars.add(c)) {
                return true;
            }
        }
        return false;
    }
}

