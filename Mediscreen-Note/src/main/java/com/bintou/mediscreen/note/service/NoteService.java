package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    NoteDTO saveNote(NoteDTO note);

    NoteDTO findNoteById(String noteId);

    List<NoteDTO> findAllNote(Integer patientId);

    NoteDTO updateNote(String noteId, NoteDTO noteDTO);


    void deleteNoteById(final String noteId);

    /*Note saveNote (Note note);
    List<Note> findByPatientId(Long patientId);
    Note findNoteById (Long id);
    List<Note> findNoteByLastNameAndFirstName(String patientLastName, String patientFirstName);
    Note updateNote(Long id, Note note);
    List<NoteDTO> findAllNote(Long id);
    Boolean deleteNoteById(Long id);*/

}
