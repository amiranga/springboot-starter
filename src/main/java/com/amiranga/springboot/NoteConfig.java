package com.amiranga.springboot;

import com.amiranga.springboot.model.Note;
import com.amiranga.springboot.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteConfig {

    @Bean
    CommandLineRunner insertDummyNotes(NoteRepository repository) {
        return args -> {
            boolean noNotes = repository.count() == 0;
            if (noNotes) {
                for (int i = 1; i < 6; i++) {
                    repository.save(new Note("Note " + i, "Description " + i));
                }
            }
        };
    }
}
