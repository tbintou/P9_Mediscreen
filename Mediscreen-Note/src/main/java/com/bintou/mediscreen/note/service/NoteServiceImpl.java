package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.exception.ResourceNotFoundException;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;
import com.bintou.mediscreen.note.util.NoteMapper;
import com.bintou.mediscreen.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;


    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public NoteDTO saveNote(final NoteDTO noteDTO) {

        Note noteAdded = noteRepository.save(noteMapper.toNote(noteDTO));

        return noteMapper.toNoteDTO(noteAdded);
    }

    public NoteDTO findNoteById(final String noteId) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La note avec cet id n'existe pas"));

        return noteMapper.toNoteDTO(note);
    }

    public List<NoteDTO> findAllNote(final Long patientId) {

        List<Note> notes = noteRepository.findByPatientId(patientId);

        List<NoteDTO> allNote = notes.stream()
                .map(note -> noteMapper.toNoteDTO(note))
                .collect(Collectors.toList());

        return allNote;
    }

    public NoteDTO updateNote(final String noteId, final NoteDTO noteDTO) {

        noteRepository.findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "L'Id non trouvé"));

        Note noteToUpdate = noteMapper.toNote(noteDTO);
        noteToUpdate.setId(noteId);
        Note noteUpdated = noteRepository.save(noteToUpdate);

        return noteMapper.toNoteDTO(noteUpdated);
    }

    public void deleteNoteById(final String noteId) {

        noteRepository.findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("L'Id non trouvé"));

        noteRepository.deleteById(noteId);

    }

}
