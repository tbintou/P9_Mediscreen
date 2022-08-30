package com.bintou.mediscreen.proximity;

import com.bintou.mediscreen.model.Patient;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mediscreen-patient", url = "${patient.serviceUrl}")
public interface PatientProximity {

    @GetMapping("/api/patients/{id}")
    ResponseEntity<Patient> findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/family")
    ResponseEntity<List<Patient>> findPatientByLastName(@RequestParam(value = "lastName") String lastName);
}
