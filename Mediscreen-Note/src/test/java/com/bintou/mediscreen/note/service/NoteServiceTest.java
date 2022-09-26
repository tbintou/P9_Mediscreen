package com.bintou.mediscreen.note.service;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;
import com.bintou.mediscreen.note.repository.NoteRepository;
import com.bintou.mediscreen.note.util.NoteMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={ MediscreenNoteApplication.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    private LocalDate date = LocalDate.of(2021,12,31);

    private Note note = new Note();

    private static NoteDTO noteDTO = new NoteDTO();


    @BeforeAll
    public void init() throws ParseException {

        note.setId("632d7cae46a20434f65d972e");
        note.setPatientId(2);
        note.setDateNote(date);
        note.setNote("note");

        noteDTO.setId("632d7cae46a20434f65d972e");
        noteDTO.setPatientId(2);
        noteDTO.setDateNote(date);
        noteDTO.setNote("note 1");
        noteService.saveNote(noteDTO);
    }

    @AfterAll
    public void clean() {
        noteRepository.delete(note);
    }

    @Test
    public void createNoteTest() {
       note = noteRepository.save(note);
       Assertions.assertNotNull(note.getId());
       Assertions.assertEquals(note.getPatientId(), 2);

       noteService.saveNote(noteDTO);
       Assertions.assertNotNull(note.getId());
       Assertions.assertEquals(note.getPatientId(), 2);
    }

    @Test
    public void findNoteByIdTest() {
        String id = "632d84a1b2b02327f3e9be47";
        noteService.findNoteById(id);

        assertEquals(2, note.getPatientId());
        assertEquals(date, note.getDateNote());
        assertEquals("note", note.getNote());
    }

   /* @Test
    public void findNoteByIdReturnNullTest() {
        String id = "noteId";

        Mockito.when(noteService.findNoteById(id)).thenThrow(new ResourceNotFoundException("La note avec cet id n'existe pas"));
        assertTrue(true);
        //assertEquals(id, "La note avec cet id n'existe pas");
        //assertThrows(ResourceNotFoundException.class,this::findNoteByIdReturnNullTest);
    }*/

    @Test
    public void findAllNoteTest() {
        int id = 2;
        List<NoteDTO> patientList = noteService.findAllNote(2);
        assertTrue(patientList.size() > 0);
    }

    @Test
    public void updateNoteTest() {

        note = noteRepository.save(note);
        assertEquals(note.getPatientId(), 2);

        noteService.updateNote(noteDTO.getId(), noteDTO);
        assertEquals(noteDTO.getId(), "632d7cae46a20434f65d972e");
    }

    @Test
    public void deleteNoteByIdTest() {
        String id = "632d7cae46a20434f65d972e";
        noteService.deleteNoteById(id);
        Optional<Note> optionalPatient = noteRepository.findById(id);
        assertFalse(optionalPatient.isPresent());
    }

}
