package com.bintou.mediscreen.service;

import com.bintou.mediscreen.model.Patient;
import com.bintou.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    @Override
    public List<Patient> findPatientByLastNameAndFirstName(String lastName, String firstName) {
        return patientRepository.findPatientByLastNameAndFirstName(lastName, firstName);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()){
            return null;
        }
        Patient updatePatient = patientOptional.get();
        updatePatient.setLastName(patient.getLastName());
        updatePatient.setFirstName(patient.getFirstName());
        updatePatient.setBirthDate(patient.getBirthDate());
        updatePatient.setAdress(patient.getAdress());
        updatePatient.setGender(patient.getGender());
        updatePatient.setPhone(patient.getPhone());
        patientRepository.save(updatePatient);
        return updatePatient;
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Boolean deletePatientById(Long id) {
        boolean deletePatient = false;
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()){
            patientRepository.deleteById(id);
            deletePatient = true;
        }
        return deletePatient;
    }
}
