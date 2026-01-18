package com.example.cornellnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class CornellNoteApplication {
  private CornellNoteApplication() {}

  public static void main(final String[] args) {
    SpringApplication.run(CornellNoteApplication.class, args);
  }
}
