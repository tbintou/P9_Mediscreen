package com.bintou.mediscreen.note.repository;

import com.bintou.mediscreen.note.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class NoteRepositoryTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void noteTest() throws ParseException {
        String dateNoteStr = "2022-08-22 15:17:32";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateNote = LocalDateTime.parse(dateNoteStr, formatter);

        Note note = new Note();
        note.setId(1L);
        note.setPatientLastName("Jack");
        note.setPatientFirstName("Test");
        note.setNote("Rien à signaler");
        note.setDateNote(dateNote);

        // Save
        note = noteRepository.save(note);
        Assertions.assertNotNull(note.getId());
        Assertions.assertEquals(note.getPatientLastName(), "Jack");

        // FindByPatientId
        Long patientId = 7L;
        note.setPatientId(patientId);
        List<Note> notePatientList = noteRepository.findByPatientId(patientId);
        Assertions.assertTrue(notePatientList.size() > 0);

        //FindById
        Long id = 1L;
        note.setId(id);
        Optional<Note> noteOptional = noteRepository.findById(id);
        Assertions.assertTrue(noteOptional.isPresent());

        //FindByLastName
        String lastName = "Jack";
        String firstName = "Test";
        List<Note> noteListByLastNameAndFirstName = noteRepository.findByPatientLastNameAndPatientFirstName(lastName, firstName);
        Assertions.assertTrue(noteListByLastNameAndFirstName.size() > 0);

        // Update
        note.setPatientLastName("TestJean");
        note = noteRepository.save(note);
        Assertions.assertEquals(note.getPatientLastName(), "TestJean");

        //FindAll
        List<Note> noteList = noteRepository.findAll();
        Assertions.assertTrue(noteList.size() > 0);

        //Delete
        Long idNote = note.getId();
        noteRepository.delete(note);
        Optional<Note> optionalNote = noteRepository.findById(idNote);
        Assertions.assertFalse(optionalNote.isPresent());

    }

    public static Date parseDate(String myDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(myDate);
        long timeInMillis = date.getTime();
        return new Date(timeInMillis);
    }
}
