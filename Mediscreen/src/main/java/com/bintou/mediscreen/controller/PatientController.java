package com.bintou.mediscreen.controller;

import com.bintou.mediscreen.exception.ValidationErrorHandlerController;
import com.bintou.mediscreen.model.Patient;
import com.bintou.mediscreen.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bintou.mediscreen.exception.ValidationErrorHandlerController.getBindingResultErrors;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Slf4j
@Api(tags = "Données API des patients")
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patients")
    @ApiOperation("Créer un nouveau patient")
    public ResponseEntity<Object> createdPatient(@RequestBody @Valid Patient patient, BindingResult bindingResult) throws ValidationErrorHandlerController {
        ResponseEntity<Object> message = getBindingResultErrors(bindingResult);
        if (message != null) throw new ValidationErrorHandlerController();

        Patient newPatient = patientService.savePatient(patient);
        log.info("Le nouveau patient est créé avec succès et son id est : " + newPatient.getId());
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @GetMapping("/patients/{id}")
    @ApiOperation("Chercher un patient par son id")
    public ResponseEntity<Object> findPatientById(@PathVariable(value = "id") Long id) {
        Patient patient = patientService.findPatientById(id);
        if (patient == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun patient n'a été trouvé avec cet id : " + id);
        }
        log.info("Id du patient est égale à : " + id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/patients/patient")
    @ApiOperation("Chercher un patient par son nom de famille")
    public ResponseEntity<List<Patient>> findPatientByLastNameAndFirstName(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName) {
        List<Patient> patientList = patientService.findPatientByLastNameAndFirstName(lastName, firstName);
        if (patientList.isEmpty()) {
            log.error("Impossible de trouver le(s) patient(s) avec ce nom de famille : " + lastName + " et ce prénom : " + firstName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Le(s) patient(s) trouver avec le nom de famille est : " + lastName);
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @PutMapping("/patients/{id}")
    @ApiOperation("Mise à jour les données du patient par son id")
    public ResponseEntity<Object> updatePatient(@PathVariable(value = "id") Long id, @Valid @RequestBody Patient patient, BindingResult result) throws ValidationErrorHandlerController {
        ResponseEntity<Object> messages = getBindingResultErrors(result);
        if (messages != null) throw new ValidationErrorHandlerController();

        Patient updatePatient = patientService.updatePatient(id, patient);
        log.info("Les informations du patient ont été mise à jour avec succès et son id est : " + id);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @GetMapping("/patients")
    @ApiOperation("Chercher la liste des patients")
    public ResponseEntity<List<Patient>> findAllPatients() {
        List<Patient> patientList = patientService.findAllPatients();
        if (patientList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    @ApiOperation("Supprimer un patient par son id")
    public ResponseEntity<Object> deletePatientById(@PathVariable(value = "id") Long id) {
        Boolean deleted = patientService.deletePatientById(id);
        if (!deleted) {
            log.error("Échec de la suppression du patient avec l'id : " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il n'existe pas de patient avec cet id : " + id);
        }
        log.info("Le patient lié à l'id : " + id + " a été supprimer avec succès");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
