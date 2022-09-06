package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.config.FeignBadResponseWrapper;
import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RapportServiceTest {

    /*private Patient patient = new Patient();
    private Note note = new Note();

    @MockBean
    private NoteProximity noteProximity;

    @MockBean
    private PatientProximity patientProximity;*/

    HttpHeaders headers = new HttpHeaders();
    private Patient patient = new Patient();
    private Note note = new Note();

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
        assertEquals(77, (int) patientAge);
    }

    @Test
    public void getPatientInfoReturnNullTest() {
       /*Long id = 40L;
       patient.setId(id);
       patient.setLastName("Jean");
       patient.setFirstName("");
       patient.setGender("");
       patient.setBirthDate(LocalDate.ofEpochDay(40L));*/

       RapportService rapportService1 = mock(RapportService.class);
       when(rapportService1.getPatientInfo(40L))
               .thenThrow(new FeignBadResponseWrapper(404, headers, "Bad request"));
        assertNull(patient.getId());
    }

    @Test
    public void getPatientAssessmentNoneTest() {
        List<Note> noteList = rapportService.getPatientNote(1L);
        for (Note note : noteList) {
            System.out.println("note = " + note.getNote());
        }
        String risk = rapportService.calculateRiskByPatientId(1L);
        System.out.println("risque = " + risk);
        assertEquals("Aucun risque (None)", risk);
    }

    @Test
    public void getPatientAssessmentBorderlineTest() {
        List<Note> noteList = rapportService.getPatientNote(8L);
        for (Note note : noteList) {
            System.out.println("note = " + note.getNote());
        }
        String risk = rapportService.calculateRiskByPatientId(8L);
        System.out.println("risque = " + risk);
        assertEquals("Aucun risque (None)", risk);

    }
}
