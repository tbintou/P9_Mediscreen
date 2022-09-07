package com.bintou.mediscreen.rapport.proximity;

import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;
import com.bintou.mediscreen.rapport.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "mediscreen", url = "${patient.serviceUrl}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface PatientProximity {

    @GetMapping("/api/patients/{id}")
    ResponseEntity<Patient> findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/patient")
    ResponseEntity<List<Patient>> findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName);
}
