package com.bintou.mediscreen.front.proximity;

import com.bintou.mediscreen.front.model.Patient;
import com.bintou.mediscreen.rapport.config.FeignPropagateBadRequestsConfiguration;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "mediscreen", url = "${mediscreen.serviceUrl:http://localhost:8081}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface PatientProximity {

    @PostMapping("/api/patients/valid")
    @ApiOperation("Créer un nouveau patient")
    ResponseEntity<Object> createdPatient(Patient patient);

    @GetMapping("/api/patients/patient/{id}")
    @ApiOperation("Chercher un patient par son id")
    Patient findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/patient/family")
    @ApiOperation("Chercher un patient par son nom de famille")
    List<Patient> findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName);

    @PutMapping("/api/patients/patient/{id}")
    @ApiOperation("Mise à jour les données du patient par son id")
    Boolean updatePatient(@PathVariable(value = "id") Long id, Patient patient);

    @GetMapping("/api/patients")
    @ApiOperation("Chercher la liste des patients")
    List<Patient> findAllPatients();

    @DeleteMapping("/api/patients/patientId/{id}")
    @ApiOperation("Supprimer un patient par son id")
    ResponseEntity<Object> deletePatientById(@PathVariable(value = "id") Long id);
}