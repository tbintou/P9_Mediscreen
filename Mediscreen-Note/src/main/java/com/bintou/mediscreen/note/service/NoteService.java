package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.model.NoteDTO;

import java.util.List;

public interface NoteService {

    NoteDTO saveNote(NoteDTO note);

    NoteDTO findNoteById(String noteId);

    List<NoteDTO> findAllNote(Long patientId);

    NoteDTO updateNote(String noteId, NoteDTO noteDTO);

    void deleteNoteById(final String noteId);

}
