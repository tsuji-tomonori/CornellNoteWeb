package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.ExportJob;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportJobRepository extends JpaRepository<ExportJob, String> {
  List<ExportJob> findByNoteIdOrderByCreatedAtDesc(String noteId);
}
