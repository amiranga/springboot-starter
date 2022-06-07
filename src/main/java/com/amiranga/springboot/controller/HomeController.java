package com.amiranga.springboot.controller;

import com.amiranga.springboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        long noteCount = noteRepository.count();
        model.addAttribute("noteCount", noteCount);
        return "home";
    }
}
