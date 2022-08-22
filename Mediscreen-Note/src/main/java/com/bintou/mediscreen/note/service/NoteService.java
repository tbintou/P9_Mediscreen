package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note saveNote (Note notes);
    Note findNoteById (Long id);
    List<Note> findNoteByLastNameAndFirstName(String patientLastName, String patientFirstName);
    Note updateNote(Long id, Note note);
    List<Note> findAllNote();
    Boolean deleteNoteById(Long id);

}
