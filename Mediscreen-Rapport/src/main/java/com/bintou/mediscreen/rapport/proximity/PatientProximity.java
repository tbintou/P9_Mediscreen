package com.bintou.mediscreen.rapport.proximity;

import com.bintou.mediscreen.rapport.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "mediscreen", url = "${patient.serviceUrl:http://localhost:8081}")
public interface PatientProximity {

    @GetMapping("/api/patients/patient/{id}")
    Patient findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/patient/family")
    Patient findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName);
}
