package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RapportServiceTest {

    /*private Patient patient = new Patient();
    private Note note = new Note();

    @MockBean
    private NoteProximity noteProximity;

    @MockBean
    private PatientProximity patientProximity;*/

    @Autowired
    private RapportService rapportService;


  /*  @Test
    public void testGetPatientAge() {
        Integer patientAge = rapportService.getPatientAge(patientInfo.getBirthDate());
        when(rapportService.getPatientInfo(1L)).thenReturn(LocalDate.ofEpochDay(1L));
        Assertions.assertEquals(55, (int) patientAge);
        verify(patient).getBirthDate();
    }*/




   @Test
    public void getPatientAgeTest() {
        Patient patientInfo = rapportService.getPatientInfo(1L);
        System.out.println("birthDate = " + patientInfo.getBirthDate());

        Integer patientAge = rapportService.getPatientAge(patientInfo.getBirthDate());
        System.out.println("patientAge = " + patientAge);
        Assertions.assertEquals(55, (int) patientAge);
    }



    @Test
    public void getPatientAssessmentTest() {
        List<Note> noteList = rapportService.getPatientNote(9L);
        for (Note note : noteList) {
            System.out.println("note = " + note.getNote());
        }
        String risk = rapportService.calculateRiskByPatientId(9L);
        System.out.println("risque = " + risk);
        Assertions.assertEquals("Aucun risque (None)", risk);
    }
}
