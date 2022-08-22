package com.bintou.mediscreen.note.controller;

import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.service.NoteService;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@Api(tags = "Données API des patients")
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping("/notes")
   // @ApiOperation(value = "Créer une nouvelle note")
    public ResponseEntity<Object> createdNote(@RequestBody Note note) {
        Note noteById = noteService.findNoteById(note.getId());
        if (noteById == null) {
            log.info("Erreur de création des notes avec l'ID : " + note.getId());
            return ResponseEntity.badRequest().build();
        }
        Note newNote = noteService.saveNote(note);
        log.info("Les Notes liées à l'id : " + newNote.getId() + " ont été créé avec succès.");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
