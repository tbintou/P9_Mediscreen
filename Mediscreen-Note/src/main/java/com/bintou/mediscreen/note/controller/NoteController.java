package com.bintou.mediscreen.note.controller;

import com.bintou.mediscreen.note.model.NoteDTO;
import com.bintou.mediscreen.note.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Slf4j
@Api(tags = "API de l'enregistrement des notes patients")
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Créer une nouvelle note")
    public NoteDTO createdNote(@RequestBody final NoteDTO noteDTO) {

        log.debug(" Méthode POST /api/notes");

        NoteDTO noteAdded = noteService.saveNote(noteDTO);

        log.info(" *** Note ajoutée avec succès");

        return noteAdded;
    }

    @GetMapping("/note/{id}")
    @ApiOperation(value = "Chercher les notes par id")
    public NoteDTO findNoteById(@PathVariable("id") final String noteId) {

        log.debug("Méthode GET /api/note/{id}");

        NoteDTO noteFound = noteService.findNoteById(noteId);

        log.info(" *** Note retournée avec succès");

        return noteFound;
    }

    @PutMapping("/notes/patient/{id}")
    @ApiOperation(value = "Mettre à jour les notes du patient par id")
    public NoteDTO updateNote(@PathVariable("id") final String noteId, @RequestBody final NoteDTO noteDTO) {

        log.debug(" Méthode POST /api/notes/patient/{id} : {}", noteId);

        NoteDTO noteUpdated = noteService.updateNote(noteId, noteDTO);

        log.info(" *** Note mise à jour avec succès");

        return noteUpdated;
    }

    @GetMapping("/notes/list/{id}")
    @ApiOperation(value = "Afficher la liste des notes des patients")
    public List<NoteDTO> findAllNote(@PathVariable("id") final Long patientId) {

        log.debug("Méthode GET /api/notes/list/{id}");

        List<NoteDTO> allNote = noteService.findAllNote(patientId);

        log.info(" *** La liste des notes est retournée avec succès");

        return allNote;
    }

    @DeleteMapping("/notes/note/{id}")
    @ApiOperation(value = "Supprimer les notes du patient par son id")
    public void deleteNoteById(@PathVariable("id") final String noteId) {

        log.debug("Méthode GET /api/notes/note/{id}");

        noteService.deleteNoteById(noteId);

        log.info(" *** Note supprimée avec succès");
    }

}
