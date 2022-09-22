package com.bintou.mediscreen.front.proximity;

import com.bintou.mediscreen.front.model.Note;
import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-note", url = "${note.serviceUrl:http://localhost:8082}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface NoteProximity {

    @PostMapping("/api/notes")
    @ApiOperation(value = "Créer une nouvelle note")
    Note createdNote(Note note);

    @GetMapping("/api/notes/patient/{patientId}")
    List<Note> findByPatientId(@PathVariable(value = "patientId") Long patientId);

    @GetMapping("/api/notes/{id}")
    @ApiOperation(value = "Chercher les notes par id")
    Note findNoteById(@PathVariable Long id);

    @GetMapping("/api/notes/patient")
    List<Note> findNoteByLastNameAndFirstName(@RequestParam("lastName") String patientLastName, @RequestParam("firstName") String patientFirstName);

    @PutMapping(value = "/api/notes/{id}")
    @ApiOperation(value = "Mettre à jour les notes du patient par id")
    Note updateNotes(@PathVariable Long id, Note note);

    @GetMapping("/api/notes")
    @ApiOperation(value = "Afficher la liste des notes des patients")
    List<Note> findAllNote();

    @DeleteMapping("/api/notes/note/{id}")
    @ApiOperation(value = "Supprimer les notes du patient par son id")
    void deleteNoteById(@PathVariable("id") Long id);

}