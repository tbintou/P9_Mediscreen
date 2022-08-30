package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.model.Status;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@PropertySource("declencheurs")
public class RapportService {

    private final NoteProximity noteProximity;
    private final PatientProximity patientProximity;
    private final String[] declencheurs;

    private final int THIRTEEN = 30;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;
    private final int SIX = 6;
    private final int SEVEN = 7;
    private final int EIGHT = 8;


    public RapportService(NoteProximity noteProximity, PatientProximity patientProximity, @Value("${listDeclencheurs}") String[] declencheurs) {
        this.noteProximity = noteProximity;
        this.patientProximity = patientProximity;
        this.declencheurs = declencheurs;
    }

    public long getPatientAge (Patient patient) {
        return ChronoUnit.YEARS.between(patient.getBirthDate(), LocalDate.now());
    }

    public Status calculRisk (Patient patient, List<Note> noteList) {
        long nbDeclencheurs = calculNbreDeclencheurs(noteList);
        long age = getPatientAge(patient);
        Status status = Status.None;


        if( (age > THIRTEEN && nbDeclencheurs >= EIGHT) ||
                ("F".equals(patient.getGender()) && age < THIRTEEN && nbDeclencheurs >= SEVEN) ||
                ("M".equals(patient.getGender()) && age < THIRTEEN && nbDeclencheurs >= FIVE)
            ) {
            status = Status.EarlyOnset;
        } else if ( (age > THIRTEEN && nbDeclencheurs >= SIX ) ||
                ("F".equals(patient.getGender()) && age < THIRTEEN && nbDeclencheurs >= FOUR && nbDeclencheurs <= SIX) ||
                ("M".equals(patient.getGender()) && age < THIRTEEN && nbDeclencheurs >= THREE && nbDeclencheurs <= FOUR)
        ) {
            status = Status.InDanger;
        } else if ( age > THIRTEEN && nbDeclencheurs >= TWO
        ) {
            status = Status.Borderline;
        }
        return status;
    }

    public long calculNbreDeclencheurs (List<Note> noteList) {

        String noteToStream = noteList.stream()
                .map(Note::getNote)
                .map(String::trim)
                .collect(Collectors.joining());

        return Arrays.stream(declencheurs)
                .filter(noteToStream::contains)
                .distinct()
                .count();
    }

    public Rapport getRapportByLastNameAndFirstName (String lastName, String firstName) {
        Patient patient = patientProximity.findPatientByLastName(lastName);
        List<Note> noteList = noteProximity.findNoteByLastNameAndFirstName(lastName, firstName);
       Status status = calculRisk(patient, noteList);
        return new Rapport(patient.getLastName(), patient.getFirstName(), patient.getGender(), getPatientAge(patient), status);
    }

    public Rapport getRapportById (long id) {
        Patient patient = patientProximity.findPatientById(id);
        List<Note> noteList = noteProximity.findNoteByLastNameAndFirstName(patient.getLastName(), patient.getFirstName());
        Status status = calculRisk(patient, noteList);
        return new Rapport(patient.getLastName(), patient.getFirstName(), patient.getGender(), getPatientAge(patient), status);
    }

}
