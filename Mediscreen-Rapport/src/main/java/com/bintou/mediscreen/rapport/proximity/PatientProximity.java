package com.bintou.mediscreen.rapport.proximity;

import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;
import com.bintou.mediscreen.rapport.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "mediscreen-patient", url = "${patient.serviceUrl}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface PatientProximity {

    @GetMapping("/api/patients/{id}")
    Patient findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/family")
    Patient findPatientByLastName(@RequestParam(value = "lastName") String lastName);
}
