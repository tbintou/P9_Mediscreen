package com.bintou.mediscreen.rapport.proximity;

import com.bintou.mediscreen.rapport.model.Note;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "mediscreen-note", url = "${note.serviceUrl:http://localhost:8082}")
public interface NoteProximity {

    @GetMapping("/api/notes/list/{id}")
    @ApiOperation(value = "Afficher la liste des notes des patients")
    List<Note> findAllNote(@PathVariable("id") final Long patientId);
}
