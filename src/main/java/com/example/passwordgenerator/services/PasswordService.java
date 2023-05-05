package com.example.passwordgenerator.services;

import com.example.passwordgenerator.models.PasswordEntity;
import com.example.passwordgenerator.repository.PasswordRepository;
import java.util.Random;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

  private final PasswordRepository passwordRepository;

  public PasswordService(PasswordRepository passwordRepository) {
    this.passwordRepository = passwordRepository;
  }

  public String getPasswordByID(Long id) {
    if (!passwordRepository.findById(id).isPresent()) {
      throw new IllegalArgumentException("Password with id " + id + "not found");
    } else {
      return passwordRepository.findById(id).get().getPassword();
    }
  }

  public String generatePassword(PasswordEntity passwordEntity) {
    String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String symbols = "§?:_(/)ˇ%*-+";
    String numbers = "0123456789";
    String characters = lowerCaseLetters;
    if (passwordEntity.getLength() < 8) {
      throw new IllegalArgumentException("Minimal length is 8");
    } else {
      if (passwordEntity.getHasUpperCase()) {
        characters += upperCaseLetters;
      }
      if (passwordEntity.getHasNumbers()) {
        characters += numbers;
      }
      if (passwordEntity.getHasSymbols()) {
        characters += symbols;
      }
    }
    if (passwordRepository.findByPassword(passwordEntity.getPassword()).isPresent()) {
      throw new IllegalArgumentException("Password already exists, please try a new one");
    }

    Random random = new Random();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < passwordEntity.getLength(); i++) {
      int index = random.nextInt(characters.length());
      password.append(characters.charAt(index));
    }

    if (passwordEntity.getHasNumbers() && !password.toString().matches(".*\\d.*")) {
      throw new IllegalArgumentException(
        "Password must contain at least one number. Please try again.");
    }
    if (passwordEntity.getHasUpperCase() && !password.toString().matches(".*[A-Z].*")) {
      throw new IllegalArgumentException(
        "Password must contain at least one uppercase letter. Please try again.");
    }
    if (passwordEntity.getHasSymbols() && !password.toString()
      .matches(".*[" + Pattern.quote(symbols) + "].*")) {
      throw new IllegalArgumentException(
        "Password must contain at least one symbol. Please try again.");
    }

    PasswordEntity entity = new PasswordEntity(passwordEntity.getLength(),
      passwordEntity.getHasUpperCase(),
      passwordEntity.getHasNumbers(), passwordEntity.getHasSymbols(), password.toString());


    passwordRepository.save(entity);

    return password.toString();
  }
}
