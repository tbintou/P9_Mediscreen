package com.bintou.mediscreen.front.proximity;

import com.bintou.mediscreen.front.model.Patient;
import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mediscreen", url = "${mediscreen.serviceUrl}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface PatientProximity {

    @GetMapping("/api/patients/{id}")
    ResponseEntity<Patient> findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/family")
    ResponseEntity<List<Patient>> findPatientByLastName(@RequestParam(value = "lastName") String lastName);
}