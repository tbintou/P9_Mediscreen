package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findByPatientId(Long patientId) {
        List<Note> noteList = noteRepository.findByPatientId(patientId);
        return noteList;
    }

    @Override
    public Note findNoteById(Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        return noteOptional.orElse(null);
    }

    @Override
    public List<Note> findNoteByLastNameAndFirstName(String patientLastName, String patientFirstName) {
        return noteRepository.findByPatientLastNameAndPatientFirstName(patientLastName, patientFirstName);
    }

    @Override
    public Note updateNote(Long id, Note note) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isEmpty()) {
            return null;
        }
        Note updateNote = noteOptional.get();
        updateNote.setNote(note.getNote());
        updateNote.setDateNote(note.getDateNote());
        updateNote.setPatientId(note.getPatientId());
        noteRepository.save(updateNote);
        return updateNote;
    }

    @Override
    public List<Note> findAllNote() {
        return noteRepository.findAll();
    }

    @Override
    public Boolean deleteNoteById(Long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
