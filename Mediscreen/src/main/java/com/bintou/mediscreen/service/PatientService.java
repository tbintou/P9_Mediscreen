package com.bintou.mediscreen.service;

import com.bintou.mediscreen.model.Patient;

import java.util.List;

public interface PatientService{
    Patient savePatient(Patient patient);
    Patient findPatientById(Long id);
    List<Patient> findPatientByLastNameAndFirstName(String lastName, String firstName);
    Patient updatePatient(Long id, Patient patient);
    List<Patient> findAllPatients();
    Boolean deletePatientById(Long id);
}
