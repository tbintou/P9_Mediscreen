package com.bintou.mediscreen.repository;

import com.bintou.mediscreen.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void patientTest() throws ParseException {
        String birthdateStr = "1945-06-24";
        LocalDate birthdate = LocalDate.parse(birthdateStr);
        Patient patient = new Patient();

        patient.setId(2L);
        patient.setLastName("TestNone");
        patient.setFirstName("Test");
        patient.setBirthDate(birthdate);
        patient.setAdress("2 High St");
        patient.setGender("F");
        patient.setPhone("200-333-4444");

        // Save
        patient = patientRepository.save(patient);
        Assertions.assertNotNull(patient.getId());
        assertEquals(patient.getLastName(), "TestNone");

        //FindById
        Long id = 1L;
        patient.setId(id);
        Optional<Patient> patientOptional = patientRepository.findById(id);
        assertTrue(patientOptional.isEmpty());

        //FindByLastName
        String lastName = "TestNone";
        List<Patient> patientListByLastName = patientRepository.findPatientByLastName(lastName);
        assertTrue(patientListByLastName.size() > 0);

        // Update
        patient.setLastName("TestJean");
        patient = patientRepository.save(patient);
        assertEquals(patient.getLastName(), "TestJean");

        //FindAll
        List<Patient> patientList = patientRepository.findAll();
        Assertions.assertTrue(patientList.size() > 0);

        //Delete
        Long idPatient = patient.getId();
        patientRepository.delete(patient);
        Optional<Patient> optionalPatient = patientRepository.findById(idPatient);
        assertFalse(optionalPatient.isPresent());

    }

    public static Date parseDate(String myDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(myDate);
        long timeInMillis = date.getTime();
        return new Date(timeInMillis);
    }
}
