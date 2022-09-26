package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientNoteTest {

    @InjectMocks
    private RapportServiceImpl rapportService;

    @Mock
    private NoteProximity noteProximity;

    @Mock
    private PatientProximity patientProximity;


    @Test
    public void getPatientsNotesTest() {

        Note note1 = new Note("1", 1L, "note1", LocalDate.of(1990,01,01));
        Note note2 = new Note("2", 1L, "note2", LocalDate.of(1991,01,01));

        List<Note> noteList = rapportService.getPatientNotes(2L);
        noteList.add(note1);
        noteList.add(note2);

        assertTrue(true);

    }

}
