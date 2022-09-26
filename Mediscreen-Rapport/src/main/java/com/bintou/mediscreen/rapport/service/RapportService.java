package com.bintou.mediscreen.rapport.service;

import com.bintou.mediscreen.rapport.model.Patient;
import com.bintou.mediscreen.rapport.model.Rapport;

public interface RapportService {
    
    Rapport getPatientRapport(Long patientId);
    
    Patient getPatient(String lastName, String firstName);
}
