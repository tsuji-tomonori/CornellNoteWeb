package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.Notebook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, String> {
  List<Notebook> findByUserIdOrderBySortOrderAsc(String userId);
}
