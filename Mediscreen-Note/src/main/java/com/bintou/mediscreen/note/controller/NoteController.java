package com.bintou.mediscreen.note.controller;

import com.bintou.mediscreen.note.exception.ResourceNotFoundException;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.service.NoteService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Slf4j
@Api(tags = "API de l'enregistrement des notes patients")
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping("/notes")
    @ApiOperation(value = "Créer une nouvelle note")
    public ResponseEntity<Object> createdNote(@RequestBody @Valid Note note) {
        Note newNote = noteService.saveNote(note);
        log.info("Les Notes liées à l'id : " + newNote.getId() + " ont été créé avec succès.");
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @GetMapping("/notes/patient/{patientId}")
    @ApiOperation("Obtenir toutes les notes par l'ID d'un patient")
    public ResponseEntity<List<Note>> findByPatientById(@PathVariable(value = "patientId") Long patientId) {
        List<Note> noteList = noteService.findByPatientId(patientId);
        if (noteList.isEmpty()) {
             throw new ResourceNotFoundException("Impossible de trouver des notes avec ce PatientId : " + patientId);
        }
        log.info("Voici les notes qui ont été avec ce PatientId : " + patientId);
        return ResponseEntity.ok(noteList);
    }

    @GetMapping("/notes/{id}")
    @ApiOperation(value = "Chercher les notes par id")
    public ResponseEntity<Note> findNoteById(@PathVariable Long id) {
        Note noteId = noteService.findNoteById(id);
        if (noteId == null) {
            throw new ResourceNotFoundException("Ils n'existent pas de notes avec cet ID : " + id);
        }
        log.info("Id du note est égale à : " + id);
        return new ResponseEntity<>(noteId, HttpStatus.OK);
    }

    @GetMapping("/notes/patient")
    @ApiOperation(value = "Chercher les notes du patient par son nom de famille et son prénom")
    public ResponseEntity<List<Note>> findNoteByLastNameAndFirstName(@RequestParam("lastName") String patientLastName, @RequestParam("firstName") String patientFirstName) {
        log.info("Obtenir les Notes du patient par son nom et son prénom : {} - {} : ", patientLastName, patientFirstName);
        List<Note> noteList = noteService.findNoteByLastNameAndFirstName(patientLastName, patientFirstName);
        if (noteList.isEmpty()) {
            throw new ResourceNotFoundException("Impossible de trouver des notes sur ce patient avec cet nom de famille : " + patientLastName + " et ce prénom : " + patientFirstName);
        }
        log.info("Les notes du patient trouver avec cet nom de famille : " + patientLastName + " et ce prénom : " + patientFirstName + " est : ");
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @PutMapping(value = "/notes/{id}")
    @ApiOperation(value = "Mettre à jour les notes du patient par id")
    public ResponseEntity<Note> updateNotes(@PathVariable Long id, @RequestBody @Valid Note note) {
        Note noteId = noteService.findNoteById(id);
        if (noteId == null) {
            throw new ResourceNotFoundException("Ils n'existent pas de notes avec cet ID : " + id);
        }
        Note updateNote = noteService.updateNote(id, note);
        log.info("Les notes du patient ont été mise à jour avec succès et son id est : " + id);
        return new ResponseEntity<>(updateNote, HttpStatus.OK);
    }

    @GetMapping("/notes")
    @ApiOperation(value = "Afficher la liste des notes des patients")
    public ResponseEntity<List<Note>> findAllNote() {
        List<Note> noteList = noteService.findAllNote();
        if (noteList.isEmpty()) {
            throw new ResourceNotFoundException("Impossible de trouver des notes");
        }
        log.info("voici la liste des notes : ");
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    @ApiOperation(value = "Supprimer les notes du patient par son id")
    public ResponseEntity<Object> deleteNoteById(@PathVariable("id") Long id) {
        Boolean deletedNoteById = noteService.deleteNoteById(id);
        if (!deletedNoteById) {
            log.error("Échec de la suppression de note avec cet id : " + id);
            throw new ResourceNotFoundException("Il n'existe pas de note avec cet id : " + id);
        }
        log.info("La(es) note(s) lié à cet id : " + id + " a été supprimer avec succès");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
