package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.MediscreenRapportApplication;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes={ MediscreenRapportApplication.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTest {

    @InjectMocks
    private RapportServiceImpl rapportService;

    @Mock
    private NoteProximity noteProximity;

    @Mock
    private PatientProximity patientProximity;


    @Test
    public void testGetPatient() {

        Patient patient = new Patient(190L, "Test", "TestEarlyOnset",
                LocalDate.of(2004,6,19), "F", "3 Club Roaderrrrr", "300-444-5555");

        when(patientProximity.findPatientById(190L)).thenReturn(patient);

        assertEquals("F", patient.getGender());

    }

    @Test
    public void testGetPatientByLastName() {

        Patient patient = new Patient(1L, "LastName", "FirstName",
                LocalDate.of(1991,8,1), "M", "Address1", "1111111111");

        when(patientProximity.findPatientByLastNameAndFirstName("LastName", "FastName")).thenReturn(patient);

        Patient patient1 = patientProximity.findPatientByLastNameAndFirstName("LastName", "FastName");

        assertEquals("LastName", patient1.getFirstName());
        assertEquals("FirstName", patient1.getLastName());

    }

}
