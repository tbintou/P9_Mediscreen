package com.bintou.mediscreen.note.proximity;

import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mediscreen-note", url = "${note.serviceUrl}")
public interface NoteProximity {

    @GetMapping("/api/notes/patient/{patientId}")
    ResponseEntity<List<Note>> findByPatientId(@PathVariable(value = "patientId") Long patientId);

    @GetMapping("/api/note/{id}")
    NoteDTO findNoteById(@PathVariable("id") final String noteId);

    @GetMapping("/api/notes/patient")
    List<Note> findNoteByLastNameAndFirstName(@RequestParam("lastName") String patientLastName, @RequestParam("firstName") String patientFirstName);
}
