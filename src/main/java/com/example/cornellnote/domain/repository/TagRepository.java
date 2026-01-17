package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
  List<Tag> findByUserIdOrderByNameAsc(String userId);
}
