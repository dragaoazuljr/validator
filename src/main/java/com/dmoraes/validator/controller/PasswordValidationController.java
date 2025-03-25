package com.dmoraes.validator.controller;

import com.dmoraes.validator.dto.PasswordValidationRequest;
import com.dmoraes.validator.dto.PasswordValidationResponse;
import com.dmoraes.validator.service.PasswordValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordValidationController {

    private final PasswordValidationService validationService;

    public PasswordValidationController(PasswordValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate")
    public ResponseEntity<PasswordValidationResponse> validatePassword(@RequestBody PasswordValidationRequest request) {
        boolean isValid = validationService.isValid(request.password());
        return ResponseEntity.ok(new PasswordValidationResponse(isValid));
    }
}