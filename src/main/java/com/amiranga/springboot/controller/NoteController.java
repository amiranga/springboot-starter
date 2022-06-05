package com.amiranga.springboot.controller;

import com.amiranga.springboot.model.Note;
import com.amiranga.springboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/note")
    public ResponseEntity<List<Note>> getAllNotes() {
        try {
            List<Note> all = noteRepository.findAll();

            if (all.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
        Optional<Note> noteData = noteRepository.findById(id);

        if (noteData.isPresent()) {
            return new ResponseEntity<>(noteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/note")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note saved = noteRepository.save(new Note(note.getTitle(), note.getDescription()));
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
        Optional<Note> noteData = noteRepository.findById(id);

        if (noteData.isPresent()) {
            Note existingNote = noteData.get();
            existingNote.setTitle(note.getTitle());
            existingNote.setDescription(note.getDescription());
            return new ResponseEntity<>(noteRepository.save(existingNote), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") long id) {
        try {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/note")
    public ResponseEntity<HttpStatus> deleteAllNotes() {
        try {
            noteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
