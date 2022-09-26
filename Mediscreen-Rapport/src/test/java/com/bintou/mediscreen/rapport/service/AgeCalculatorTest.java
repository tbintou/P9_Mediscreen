package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AgeCalculatorTest {

    @InjectMocks
    private RapportServiceImpl rapportService;

    @Mock
    private NoteProximity noteProxy;

    @Mock
    private PatientProximity patientProxy;


    @Test
    public void getAgeTest() {
        LocalDate birthDate = LocalDate.now().minusYears(25);

        int age = rapportService.getAge(birthDate);

        assertEquals(25, age);
    }

    @Test
    public void getAgeForLessThanAYearTest() {
        LocalDate birthDate = LocalDate.now().minusMonths(11);

        int age = rapportService.getAge(birthDate);

        assertEquals(1, age);
    }

    @Test
    public void getAgeForInvalidNullValueDateOfBirthInputTest() {
        LocalDate birthDate = null;

        assertThatNullPointerException()
                .isThrownBy(() -> rapportService.getAge(birthDate));
    }

    @Test
    public void getAgeForInvalidFutureDateOfBirthInputTest() {
        int age = 1;
        LocalDate birthDate = LocalDate.now().plusYears(age);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> rapportService.getAge(birthDate));
    }

    @Test
    public void testGetAgeForInvalidNegativeValueDateOfBirthInput() {
        int age = -1;
        LocalDate birthDate = LocalDate.now().minusYears(age);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> rapportService.getAge(birthDate));
    }

}
