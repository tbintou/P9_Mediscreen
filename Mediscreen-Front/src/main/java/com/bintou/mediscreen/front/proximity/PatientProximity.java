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

@FeignClient(value = "mediscreen", url = "${mediscreen.serviceUrl}", configuration = FeignPropagateBadRequestsConfiguration.class)
public interface PatientProximity {

    @PostMapping("/api/patients")
    @ApiOperation("Créer un nouveau patient")
    ResponseEntity<Object> createdPatient(@RequestBody @Valid Patient patient, BindingResult bindingResult);

    @GetMapping("/api/patients/{id}")
    @ApiOperation("Chercher un patient par son id")
    ResponseEntity<Patient> findPatientById(@PathVariable(value = "id") Long id);

    @GetMapping("/api/patients/patient")
    @ApiOperation("Chercher un patient par son nom de famille")
    ResponseEntity<List<Patient>> findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName);

    @PutMapping("/api/patients/{id}")
    @ApiOperation("Mise à jour les données du patient par son id")
    ResponseEntity<Object> updatePatient(@PathVariable(value = "id") Long id, @Valid @RequestBody Patient patient, BindingResult result);

    @GetMapping("/api/patients")
    @ApiOperation("Chercher la liste des patients")
    ResponseEntity<List<Patient>> findAllPatients();

    @DeleteMapping("/api/patients/{id}")
    @ApiOperation("Supprimer un patient par son id")
    ResponseEntity<Object> deletePatientById(@PathVariable(value = "id") Long id);
    @GetMapping("/api/patients/family")
    ResponseEntity<List<Patient>> findPatientByLastName(@RequestParam(value = "lastName") String lastName);
}