package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.model.Status;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RapportService.class})
@ExtendWith(SpringExtension.class)
public class RapportServiceTest {

    @MockBean
    private NoteProximity noteProximity;

    @MockBean
    private PatientProximity patientProximity;

    @Autowired
    private RapportService rapportService;

    @Test
    public void getPatientAgeTest() {
        Patient patient = mock(Patient.class);
        when(patient.getBirthDate()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals(52L, this.rapportService.getPatientAge(patient));
        verify(patient).getBirthDate();
    }

    @Test
    public void calculRiskTest() {
        Patient patient = mock(Patient.class);
        when(patient.getGender()).thenReturn("gender");
        when(patient.getBirthDate()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals(Status.None, this.rapportService.calculRisk(patient, new ArrayList<Note>()));
        verify(patient).getBirthDate();
        verify(patient, times(4)).getGender();
    }

    @Test
    public void calculNbDeclencheursTest() {
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("gender");

        ArrayList<Note> noteList = new ArrayList<>();
        noteList.add(note);
        noteList.add(new Note(1L, "Note"));
        assertEquals(0L, this.rapportService.calculNbreDeclencheurs(noteList));
        verify(note).getNote();
    }

    @Test
    public void getRapportByLastNameAndFirstNameTest() {
        Patient patient = mock(Patient.class);
        when(patient.getFirstName()).thenReturn("Nicolas");
        when(patient.getLastName()).thenReturn("Boulanger");
        when(patient.getGender()).thenReturn("M");
        when(patient.getBirthDate()).thenReturn(LocalDate.ofEpochDay(1L));
        when(this.patientProximity.findPatientByLastName(anyString())).thenReturn(patient);
        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("test");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(123L, "Note"));
        when(this.noteProximity.findNoteByLastNameAndFirstName(anyString(), anyString())).thenReturn(noteList);
        Rapport actualRapportByLastAndFirstName = this.rapportService.getRapportByLastNameAndFirstName("Doe", "Jane");
        assertEquals(52L, actualRapportByLastAndFirstName.getAge());
        assertEquals(Status.None, actualRapportByLastAndFirstName.getStatus());
        assertEquals("M", actualRapportByLastAndFirstName.getGender());
        assertEquals("Nicolas", actualRapportByLastAndFirstName.getLastName());
        assertEquals("Boulanger", actualRapportByLastAndFirstName.getFirstName());
        verify(note).getNote();
        verify(this.noteProximity).findNoteByLastNameAndFirstName(anyString(), anyString());
        verify(patient, times(2)).getBirthDate();
        verify(patient, times(5)).getGender();
        verify(patient).getFirstName();
        verify(patient).getLastName();
        verify(this.patientProximity).findPatientByLastName(anyString());
    }

    @Test
    public void getRapportByIdTest() {
        Patient patient = new Patient(1L, "Gros", "Nicolas", LocalDate.ofEpochDay(1L), "M", "42 Main St", "000-6666-777");
        patient.setFirstName("Nicolas");
        when(this.patientProximity.findPatientById(anyLong())).thenReturn(patient);
        when(this.noteProximity.findNoteByLastNameAndFirstName(anyString(), anyString())).thenReturn(new ArrayList<>());
        Rapport actualRapportById = this.rapportService.getRapportById(1);
        assertEquals(52L, actualRapportById.getAge());
        assertEquals(Status.None, actualRapportById.getStatus());
        assertEquals("M", actualRapportById.getGender());
        assertEquals("Nicolas", actualRapportById.getLastName());
        assertEquals("Nicolas", actualRapportById.getFirstName());
        verify(this.noteProximity).findNoteByLastNameAndFirstName(anyString(), anyString());
        verify(this.patientProximity).findPatientById(anyLong());
    }

}
