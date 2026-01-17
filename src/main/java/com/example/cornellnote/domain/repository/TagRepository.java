package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, UUID> {
  List<Tag> findByUserIdOrderByNameAsc(UUID userId);
}
