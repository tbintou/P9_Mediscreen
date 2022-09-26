package com.bintou.mediscreen.note.model;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenNoteApplication.class })
public class NoteTest {
    private Note note;
    private LocalDate date = LocalDate.now();

    @BeforeEach
    void init() {
        note = new Note();
        note.setId("632d9950d35a776550adfa4d");
        note.setPatientId(2);
        note.setDateNote(date);
        note.setNote("note");
    }

    @Test
    void testGetPatientHistoryValues() {
        assertEquals(2, note.getPatientId());
        assertEquals("632d9950d35a776550adfa4d", note.getId());
        assertEquals(date, note.getDateNote());
        assertEquals("note", note.getNote());

    }

    @Test
    void testSetPatientHistoryValues() {

        assertEquals(2, note.getPatientId());
        assertEquals("632d9950d35a776550adfa4d", note.getId());
        assertEquals(date, note.getDateNote());
        assertEquals("note", note.getNote());

        LocalDate newDate = LocalDate.of(2021,12,31);

        note.setId("newId");
        note.setPatientId(3);
        note.setDateNote(newDate);
        note.setNote("newNote");

        assertEquals(3, note.getPatientId());
        assertEquals("newId", note.getId());
        assertEquals(newDate, note.getDateNote());
        assertEquals("newNote", note.getNote());
    }

}
