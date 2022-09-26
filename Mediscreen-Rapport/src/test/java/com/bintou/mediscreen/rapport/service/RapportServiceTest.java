package com.bintou.mediscreen.rapport.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RapportServiceTest {
/*
    @Autowired
    private RapportServiceImpl rapportService;

    private Patient patient = mock(Patient.class);
    private Note note = mock(Note.class);
    HttpHeaders headers = new HttpHeaders();


    @Test
    public void getPatientAgeTest() {
        when(patient.getBirthDate()).thenReturn(LocalDate.ofEpochDay(4L));
        assertEquals(52L, this.rapportService.getPatientAge(patient));
        verify(patient).getBirthDate();
    }

    @Test
    public void getPatientTest() {
        Patient patientInfo = rapportService.getPatientInfo(4L);
        System.out.println("birthDate = " + patientInfo.getBirthDate());

    }

    @Test
    public void getPatientInfoReturnNullTest() {
        RapportServiceImpl rapportService1 = mock(RapportServiceImpl.class);
        when(rapportService1.getPatientInfo(40L))
                .thenThrow(new FeignBadResponseWrapper(404, headers, "Bad request"));
        assertEquals(0,patient.getId());
    }

    @Test
    public void getPatientAssessmentNoneTest() {
        List<Note> noteList = rapportService.getPatientNote(1L);
        for (Note note : noteList) {
            System.out.println("note = " + note.getNote());
        }
        String risk = rapportService.calculateRiskByPatientId(1L);
        System.out.println("risque = " + risk);
        assertNull(risk);
    }

    @Test
    public void getPatientAssessmentBorderlineTest() {
        List<Note> noteList = rapportService.getPatientNote(7L);
        for (Note note : noteList) {
            System.out.println("note = " + note.getNote());
        }
        String risk = rapportService.calculateRiskByPatientId(7L);
        System.out.println("risque = " + risk);
        assertNull(risk);
    }
*/
}
