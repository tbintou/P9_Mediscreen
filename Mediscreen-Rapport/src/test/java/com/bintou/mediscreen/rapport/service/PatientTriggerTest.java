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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientTriggerTest {

    @InjectMocks
    private RapportServiceImpl rapportService;

    @Mock
    private NoteProximity noteProximity;

    @Mock
    private PatientProximity patientProximity;


    @Test
    public void testGetPatientTriggers() {

        Note note1 = new Note("1", 1L, "Smoker", LocalDate.of(1990,01,01));
        Note note2 = new Note("2", 1L, "Cholesterol", LocalDate.of(1991,01,01));
        List<Note> noteList = Arrays.asList(note1, note2);
        lenient().when(noteProximity.findAllNote(1L)).thenReturn(noteList);

        int triggerCount = rapportService.getPatientTriggers(noteList);

        assertEquals(2, triggerCount);

    }

}
