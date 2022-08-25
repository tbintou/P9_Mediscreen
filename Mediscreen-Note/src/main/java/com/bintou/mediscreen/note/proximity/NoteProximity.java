package com.bintou.mediscreen.note.proximity;

import com.bintou.mediscreen.note.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "mediscreen", url = "${patient.serviceUrl}")
public interface NoteProximity {

    @GetMapping("/api/notes/patient/{patientId}")
    ResponseEntity<List<Note>> findByPatientId(@PathVariable(value = "patientId") Long patientId);
}
