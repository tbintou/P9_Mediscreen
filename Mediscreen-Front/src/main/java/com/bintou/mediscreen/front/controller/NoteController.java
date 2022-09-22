package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.Note;

import com.bintou.mediscreen.front.proximity.NoteProximity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/api")
@Slf4j
public class NoteController {

    private final NoteProximity noteProximity;

    @Autowired
    public NoteController(final NoteProximity noteProximity) {
        this.noteProximity = noteProximity;
    }

    @GetMapping("/note/{id}")
    public String addNoteForm(@PathVariable("id") final Long patientId, final Model model) {

        log.debug(" *** UI -GET /note/add/{id} called");

        Note note = new Note();
        note.setPatientId(patientId);
        model.addAttribute("note", note);

        log.info(" *** UI - note add page displyaed successfully");

        return "note/add";
    }

    @PostMapping("/note/valid")
    public String validate(@Valid final Note note, final BindingResult result) {

        log.debug(" *** UI - POST /note/validate requested");

        if (result.hasErrors()) {
            return "note/add";
        } else {
            this.noteProximity.createdNote(note);

            log.info(" *** UI - note added successfully");

            return "redirect:/api/notes/" + note.getPatientId();
        }
    }

    @GetMapping("/notes/{id}")
    public String getUpdateForm(@PathVariable("id") final Long noteId, final Model model) {

        log.debug(" *** UI - GET /note/update/{id} requested");

        Note note = this.noteProximity.findNoteById(noteId);
        model.addAttribute("note", note);

        log.info(" *** UI - note update page displyaed successfully");

        return "note/update";
    }

    @PostMapping("/notes/{id}")
    public String updateNotes(@PathVariable("id") final Long noteId, @Valid final Note note, final BindingResult result) {

        log.debug(" *** UI - POST /note/update/{id} requested");

        if (result.hasErrors()) {
            return "note/update";
        } else {
            this.noteProximity.updateNotes(noteId, note);

            log.info(" *** UI - note update successfully");

            return "redirect:/api/notes/" + note.getPatientId();
        }
    }

    @GetMapping("/notes")
    public String findAllNote(@PathVariable("id") final Long patientId, final Model model) {

        log.debug(" *** UI - GET /note/list/{id} - called");

        model.addAttribute("patientId", patientId);
        model.addAttribute("notes", this.noteProximity.findAllNote());

        log.info(" *** UI - note list returned successfully");

        return "note/list";
    }

    @GetMapping("/notes/note/{id}")
    public String deleteNoteById(@PathVariable("id") final Long noteId, @PathVariable("patientId") final Long patientId) {

        log.debug("GET /note/delete/{id}/{patientId} called");

        this.noteProximity.deleteNoteById(noteId);

        log.info(" *** UI - nnote deleted successfully");

        return "redirect:/api/notes/" + patientId;

    }

}

