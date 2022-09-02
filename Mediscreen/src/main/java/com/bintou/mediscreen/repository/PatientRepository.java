package com.bintou.mediscreen.repository;

import com.bintou.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findPatientByLastNameAndFirstName(String lastName, String firstName);
}
