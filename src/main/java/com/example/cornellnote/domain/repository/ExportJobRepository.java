package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.ExportJob;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportJobRepository extends JpaRepository<ExportJob, UUID> {
  List<ExportJob> findByNoteIdOrderByCreatedAtDesc(UUID noteId);
}
