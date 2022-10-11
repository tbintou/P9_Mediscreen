package com.bintou.mediscreen.note.util;

import com.bintou.mediscreen.note.MediscreenNoteApplication;
import com.bintou.mediscreen.note.model.Note;
import com.bintou.mediscreen.note.model.NoteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenNoteApplication.class })
public class NoteMapperTest {

    private NoteMapper noteMapper = new NoteMapper();
    private LocalDate date = LocalDate.now();


    @Test
    public void noteDOTest() {

        Note expectedNote = new Note();
        expectedNote.setPatientId(2L);
        expectedNote.setDateNote(date);
        expectedNote.setNote("note");

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId("patId");
        noteDTO.setPatientId(2L);
        noteDTO.setDateNote(date);
        noteDTO.setNote("note");

        Note result = noteMapper.toNote(noteDTO);

        assertThat(result).usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedNote);
    }

    @Test
    public void getNoteDTOTest() {

        NoteDTO expectedNoteDTO = new NoteDTO();
        expectedNoteDTO.setId("patId");
        expectedNoteDTO.setPatientId(2L);
        expectedNoteDTO.setDateNote(date);
        expectedNoteDTO.setNote("note");

        Note note = new Note();
        note.setId("patId");
        note.setPatientId(2L);
        note.setDateNote(date);
        note.setNote("note");

        NoteDTO result = noteMapper.toNoteDTO(note);

        assertThat(result).usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedNoteDTO);
    }

}
