package com.bintou.mediscreen.rapport.model;

import com.bintou.mediscreen.rapport.MediscreenRapportApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ MediscreenRapportApplication.class })
public class PatientTest {

    private Patient patient = new Patient();
    private LocalDate birthday = LocalDate.now();

    @BeforeEach
    public void init() {
        patient.setId(1L);
        patient.setLastName("Lastname");
        patient.setFirstName("Firstname");
        patient.setBirthDate(birthday);
        patient.setGender("sex");
        patient.setAdress("address");
        patient.setPhone("phone");
    }

    @Test
    public void getPatientValuesTest() {
        assertEquals("Lastname", patient.getLastName());
        assertEquals("Firstname", patient.getFirstName());
        assertEquals("sex", patient.getGender());
        assertEquals(birthday, patient.getBirthDate());
        assertEquals("address", patient.getAdress());
        assertEquals("phone", patient.getPhone());

    }

    @Test
    public void patientValuesTest() {

        assertEquals("Lastname", patient.getLastName());
        assertEquals("Firstname", patient.getFirstName());
        assertEquals("sex", patient.getGender());
        assertEquals(birthday, patient.getBirthDate());
        assertEquals("address", patient.getAdress());
        assertEquals("phone", patient.getPhone());

        LocalDate newBirthday = LocalDate.now();

        patient.setLastName("newLastname");
        patient.setFirstName("newFirstname");
        patient.setGender("newSex");
        patient.setBirthDate(newBirthday);
        patient.setAdress("newAddress");
        patient.setPhone("newPhone");

        assertEquals("newLastname", patient.getLastName());
        assertEquals("newFirstname", patient.getFirstName());
        assertEquals("newSex", patient.getGender());
        assertEquals(newBirthday, patient.getBirthDate());
        assertEquals("newAddress", patient.getAdress());
        assertEquals("newPhone", patient.getPhone());
    }
}
