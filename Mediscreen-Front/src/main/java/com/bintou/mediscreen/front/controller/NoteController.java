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

    @GetMapping("/notes/{id}")
    public String addNoteForm(@PathVariable("id") final Long patientId, final Model model) {

        log.debug(" *** Méthode - GET /api/note/form/{id}");

        Note note = new Note();
        note.setPatientId(patientId);
        model.addAttribute("note", note);

        log.info(" *** La page d'ajout de note a été distribuée avec succès");

        return "note/add";
    }

    @PostMapping("/note/valid")
    public String validate(@Valid Note note, BindingResult result, Model model, String id) {

        log.debug(" *** Méthode - POST /api/note/valid");

        if (!result.hasErrors()) {
            this.noteProximity.createdNote(note);

            model.addAttribute("note", noteProximity.findNoteById(id));
            log.info(" *** La note a été ajoutée avec succès");

            return "redirect:/api/notes/list/" + note.getPatientId();
        }
        return "note/add";
    }

    @GetMapping("/note/{id}")
    public String getUpdateForm(@PathVariable("id") final String noteId, final Model model) {

        log.debug(" *** Méthode - GET /api/note/{id}");

        Note note = this.noteProximity.findNoteById(noteId);
        model.addAttribute("note", note);

        log.info(" *** Les notes ont été mise à jour avec succès");

        return "note/update";
    }

    @PostMapping("/note/{id}")
    public String updateNotes(@PathVariable("id") String noteId, @Valid Note note, BindingResult result) {

        log.debug(" *** Méthode - POST /api/note/{id}");

        if (!result.hasErrors()) {
            return "note/update";
        } else {
            this.noteProximity.updateNotes(noteId, note);

            log.info(" *** Les notes ont été mise à jour avec succès");

            return "redirect:/api/notes/list/" + note.getId();
        }
    }

    @GetMapping("/notes/list/{id}")
    public String findAllNote(@PathVariable("id") final Long patientId, final Model model) {

        log.debug(" *** Méthode - GET /api/note/list/{id} ");

        model.addAttribute("patientId", patientId);
        model.addAttribute("notes", this.noteProximity.findAllNote(patientId));

        log.info(" *** Liste des notes retournée avec succès ");

        return "note/list";
    }

    @GetMapping("/notes/note/{id}/{patientId}")
    public String deleteNoteById(@PathVariable("id") final String noteId, @PathVariable("patientId") final Long patientId) {

        log.debug(" Méthode GET /api/notes/note/{id}/{patientId}");

        this.noteProximity.deleteNoteById(noteId);

        log.info(" *** Note supprimé avec succès");

        return "redirect:/api/notes/list/" + patientId;

    }

}

