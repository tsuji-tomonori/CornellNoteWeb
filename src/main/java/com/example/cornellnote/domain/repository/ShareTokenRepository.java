package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.ShareToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareTokenRepository extends JpaRepository<ShareToken, UUID> {
  Optional<ShareToken> findByTokenHash(String tokenHash);
}
