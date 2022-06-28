package com.cohort15adv.muzick.contollers;

import com.cohort15adv.muzick.models.Listener;
import com.cohort15adv.muzick.models.Note;
import com.cohort15adv.muzick.repositories.ListenerRepository;
import com.cohort15adv.muzick.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("/api/notes")

public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ListenerRepository listenerRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() {
        return new ResponseEntity<>("note route", HttpStatus.OK);
    }

    @PostMapping("/{listenerId}")
    public ResponseEntity<?> createNote(@PathVariable Long listenerId, @RequestBody Note newNote) {
        // TODO validate the listener id is an actual listener
        Listener listener =
                listenerRepository.findById(listenerId).orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND));
        newNote.setCreator(listener);
        Note note = noteRepository.save(newNote);
        return new ResponseEntity<>(note, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllNotes(){
        List<Note> notes = noteRepository.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteByID(@PathVariable Long id){
        Optional<Note> note = noteRepository.findById(id);

        if (note.isEmpty()) {
            return new ResponseEntity<>(("Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note.get(), HttpStatus.OK);
    }

    @GetMapping("/listener/{listenerId}")
    public ResponseEntity<List<Note>> getNotesByListener(@PathVariable Long listenerId){
        List <Note> notes = noteRepository.findAllByListener_id(listenerId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }




}
