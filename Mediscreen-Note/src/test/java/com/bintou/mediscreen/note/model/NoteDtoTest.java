package com.bintou.mediscreen.note.model;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenNoteApplication.class })
public class NoteDtoTest {

    private NoteDTO noteDto;
    private LocalDate date = LocalDate.now();

    @BeforeEach
    void init() {
        noteDto = new NoteDTO();
        noteDto.setId("632d9950d35a776550adfa4d");
        noteDto.setPatientId(2);
        noteDto.setDateNote(date);
        noteDto.setNote("note");
    }

    @Test
    void getPatientDTOValuesTest() {
        assertEquals(2, noteDto.getPatientId());
        assertEquals("632d9950d35a776550adfa4d", noteDto.getId());
        assertEquals(date, noteDto.getDateNote());
        assertEquals("note", noteDto.getNote());

    }

    @Test
    void getNotesDTOValuesTest() {

        assertEquals(2, noteDto.getPatientId());
        assertEquals("632d9950d35a776550adfa4d", noteDto.getId());
        assertEquals(date, noteDto.getDateNote());
        assertEquals("note", noteDto.getNote());

        LocalDate newDate = LocalDate.of(2021,12,31);

        noteDto.setId("newId");
        noteDto.setPatientId(4);
        noteDto.setDateNote(newDate);
        noteDto.setNote("newNote");

        assertEquals(4, noteDto.getPatientId());
        assertEquals("newId", noteDto.getId());
        assertEquals(newDate, noteDto.getDateNote());
        assertEquals("newNote", noteDto.getNote());
    }

}
