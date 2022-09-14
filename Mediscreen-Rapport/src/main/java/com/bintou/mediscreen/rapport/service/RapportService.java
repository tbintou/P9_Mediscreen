package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.config.FeignBadResponseWrapper;
import com.bintou.mediscreen.rapport.model.Note;
import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Rapport;
import com.bintou.mediscreen.rapport.model.Status;
import com.bintou.mediscreen.rapport.proximity.NoteProximity;
import com.bintou.mediscreen.rapport.proximity.PatientProximity;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class RapportService {

    private final NoteProximity noteProximity;
    private final PatientProximity patientProximity;

    private final int THIRTEEN = 30;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;
    private final int SIX = 6;
    private final int SEVEN = 7;
    private final int EIGHT = 8;


    public RapportService(NoteProximity noteProximity, PatientProximity patientProximity) {
        this.noteProximity = noteProximity;
        this.patientProximity = patientProximity;
    }

    public Patient getPatientInfo(Long patientId) {
        try {
            return patientProximity.findPatientById(patientId).getBody();
        } catch (HystrixBadRequestException he) {
            if (he instanceof FeignBadResponseWrapper) {
                return null;
            } else {
                return null;
            }
        }
    }

    public List<Note> getPatientNote(Long patientId) {
        try {
            return noteProximity.findByPatientId(patientId).getBody();
        } catch (HystrixBadRequestException he) {
            if (he instanceof FeignBadResponseWrapper) {
                return null;
            } else {
                return null;
            }
        }
    }

    public String calculateRiskByPatientId(Long patientId) {
        Patient patientInfo = getPatientInfo(patientId);
        List<Note> patientNoteList = getPatientNote(patientId);
        if (patientId == null || patientInfo == null || patientNoteList == null) return null;

        Long age = getPatientAge(patientInfo);
        String sex = patientInfo.getGender();

        Integer terms = getTotalTriggerTerms(patientNoteList);

        String assessment = "";

        if (terms == 0) {
            assessment = "Aucun risque (None)";
        }

        if (age > THIRTEEN) {
            if (terms >= TWO && terms < SIX) {
                assessment = "risque limité (Borderline)";
            } else if (terms >= SIX && terms < EIGHT) {
                assessment = "danger (In Danger)";
            } else if (terms >= EIGHT) {
                assessment = "apparition précoce (Early onset)";
            }
        } else if (age < THIRTEEN) {
            if (sex.equals("M")) {
                if (terms >= THREE && terms < FIVE) {
                    assessment = "danger (In Danger)";
                } else if (terms >= FIVE) {
                    assessment = "apparition précoce (Early onset)";
                }
            } else if (sex.equals("F")) {
                if (terms >= FOUR && terms < SEVEN) {
                    assessment = "danger (In Danger)";
                } else if (terms >= SEVEN) {
                    assessment = "apparition précoce (Early onset)";
                }
            }
        }

        return assessment;
    }

    public Long getPatientAge(Patient patient) {
        return ChronoUnit.YEARS.between(patient.getBirthDate(), LocalDate.now());
    }

    private Integer getTotalTriggerTerms(List<Note> patientNoteList) {
        Integer result = 0;
        Set<String> terms = new HashSet<>();
        terms.add("Hémoglobine A1C");
        terms.add("Microalbumine");
        terms.add("Taille");
        terms.add("Poids");
        terms.add("Fume");
        terms.add("Anormal");
        terms.add("Cholestérol");
        terms.add("Vertige");
        terms.add("Rechute");
        terms.add("Réaction");
        terms.add("Anticorps");

        for (Note note : patientNoteList) {
            for (String term : terms) {
                if (note.getNote().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))) {
                    result ++;
                }
            }
        }

        return result;
    }

}
