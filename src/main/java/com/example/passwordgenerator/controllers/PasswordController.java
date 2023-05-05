package com.example.passwordgenerator.controllers;

import com.example.passwordgenerator.models.PasswordEntity;
import com.example.passwordgenerator.services.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

  private final PasswordService passwordService;

  public PasswordController(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @PostMapping("/generate")
  public ResponseEntity generatePassword(@RequestBody PasswordEntity passwordEntity) {
    String password = passwordService.generatePassword(passwordEntity);
    return ResponseEntity.ok(password);
  }
  @GetMapping("/password/{id}")
  public ResponseEntity<String> getPasswordById(@PathVariable Long id) {
    String password = passwordService.getPasswordByID(id);
    return ResponseEntity.ok(password);
  }
}
