package com.amiranga.springboot.repository;

import com.amiranga.springboot.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByTitleContaining(String title);
}
