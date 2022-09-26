package com.bintou.mediscreen.rapport.model;

import com.bintou.mediscreen.rapport.MediscreenRapportApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenRapportApplication.class })
public class NoteTest {

    private Note note;
    private LocalDate date = LocalDate.now();

    @BeforeEach
    public void init() {
        note = new Note();
        note.setId("patId");
        note.setPatientId(1L);
        note.setDateNote(date);
        note.setNote("note");
    }

    @Test
    public void getNoteValuesTest() {
        assertEquals(1L, note.getPatientId());
        assertEquals("patId", note.getId());
        assertEquals(date, note.getDateNote());
        assertEquals("note", note.getNote());

    }

    @Test
    void noteValuesTest() {

        assertEquals(1L, note.getPatientId());
        assertEquals("patId", note.getId());
        assertEquals(date, note.getDateNote());
        assertEquals("note", note.getNote());

        LocalDate newDate = LocalDate.of(2021,12,31);

        note.setId("newId");
        note.setPatientId(3L);
        note.setDateNote(newDate);
        note.setNote("newNote");


        assertEquals(3L, note.getPatientId());
        assertEquals("newId", note.getId());
        assertEquals(newDate, note.getDateNote());
        assertEquals("newNote", note.getNote());
    }
}
