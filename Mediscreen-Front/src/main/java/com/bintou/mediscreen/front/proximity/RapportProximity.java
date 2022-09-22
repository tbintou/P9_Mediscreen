package com.bintou.mediscreen.front.proximity;

import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mediscreen-rapport", url = "${rapport.serviceUrl:http://localhost:8083}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface RapportProximity {

    @GetMapping("/rapports/patient/{patientId}")
    ResponseEntity<String> getRiskAssessment(@PathVariable(value = "patientId") Long patientId);
}