package com.example.passwordgenerator.repository;

import com.example.passwordgenerator.models.PasswordEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {
  Optional<PasswordEntity> findByPassword(String password);
}
