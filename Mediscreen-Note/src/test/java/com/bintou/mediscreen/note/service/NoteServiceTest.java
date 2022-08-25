package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.repository.NoteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest(classes={ MediscreenNoteApplication.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteServiceTest {

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    private  Note note = new Note();


    @Test
    public void saveNoteTest() {
        note.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        note.setId(42L);
        note.setPatientLastName("Jackson");
        note.setPatientId(12L);
        note.setPatientFirstName("Jane");
        note.setNote("Notes du patient");
        when(this.noteRepository.save((Note) any())).thenReturn(note);
        this.noteService.saveNote(new Note());
        verify(this.noteRepository).save((Note) any());
    }

    @Test
    public void findNoteByPatientIdTest() {
        note.setDateNote(LocalDateTime.of(2022, 8, 24, 10,20,30));
        note.setId(2L);
        note.setPatientLastName("Jean");
        note.setPatientId(4L);
        note.setPatientFirstName("Pierre");
        note.setNote("Test COVID positive ");

        List<Note> noteList = this.noteService.findByPatientId(4L);
        noteList.add(note);
        when(this.noteRepository.findByPatientId(anyLong())).thenReturn(noteList);
        Assertions.assertTrue(noteList.size() > 0);
        verify(this.noteRepository).findByPatientId(anyLong());
    }

    @Test
    public void findNoteByIdTest() {
        note.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        note.setId(42L);
        note.setPatientLastName("Jackson");
        note.setPatientId(12L);
        note.setPatientFirstName("Jane");
        note.setNote("Notes du patient");

        Optional<Note> noteOptional = Optional.<Note>of(note);
        when(this.noteRepository.findById(anyLong())).thenReturn(noteOptional);
        Optional<Note> notesById = Optional.ofNullable(this.noteService.findNoteById(42L));
        Assertions.assertTrue(notesById.isPresent());
        verify(this.noteRepository).findById(anyLong());
    }

    @Test
    public void findNoteByLastNameAndFirstNameTest() {
        ArrayList<Note> noteList = new ArrayList<Note>();
        when(this.noteRepository.findByPatientLastNameAndPatientFirstName(anyString(), anyString())).thenReturn(noteList);
        List<Note> notesByLastAndFirstName = this.noteService.findNoteByLastNameAndFirstName("Jackson", "Jane");
        Assertions.assertTrue(notesByLastAndFirstName.isEmpty());
        verify(this.noteRepository).findByPatientLastNameAndPatientFirstName(anyString(), anyString());
    }

    @Test
    public void updateNoteTest() {
        Note note = new Note();
        note.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        note.setId(42L);
        note.setPatientLastName("Jackson");
        note.setPatientId(12L);
        note.setPatientFirstName("Jane");
        note.setNote("Mise à jour des notes");
        Optional<Note> noteOptional = Optional.<Note>of(note);
        when(this.noteRepository.findById(anyLong())).thenReturn(noteOptional);
        when(this.noteRepository.save((Note) any())).thenReturn(note);
        this.noteService.updateNote(note.getId(), note);
        verify(this.noteRepository).findById(anyLong());
    }

    @Test
    public void findAllNoteTest() {
        ArrayList<Note> noteList = new ArrayList<Note>();
        when(this.noteRepository.findAll()).thenReturn(noteList);
        List<Note> notefindAll = this.noteService.findAllNote();
        Assertions.assertTrue(notefindAll.isEmpty());
        verify(this.noteRepository).findAll();
    }

    @Test
    public void deleteNoteByIdTest() {
        note.setDateNote(LocalDateTime.of(2, 3, 1, 1, 1));
        note.setId(32L);
        note.setPatientLastName("Test");
        note.setPatientId(16L);
        note.setPatientFirstName("Janessss");
        note.setNote("Notes du patient");

        Optional<Note> noteOptional = Optional.<Note>of(note);
        when(this.noteRepository.findById(anyLong())).thenReturn(noteOptional);
        Optional<Boolean> notesById = Optional.ofNullable(this.noteService.deleteNoteById(32L));
        Assertions.assertTrue(notesById.isPresent());
        Assertions.assertFalse(false);
        verify(this.noteRepository).findById(anyLong());
    }

}
