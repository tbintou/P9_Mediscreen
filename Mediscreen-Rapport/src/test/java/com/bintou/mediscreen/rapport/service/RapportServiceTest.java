package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class RapportServiceTest {

    @Autowired
    private RapportService rapportService;

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
